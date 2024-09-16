const express = require('express');
const Joi = require('joi');
const app = express();
const port = 5000;
const mongoose = require('mongoose');
app.use(express.json());

mongoose.connect('mongodb://127.0.0.1:27017/millionaire', { useNewUrlParser: true })
    .then(() => console.log('Connected to MongoDB!'))
    .catch(error => console.error('Could not connect to MongoDB... ', error));


const questionSchema = new mongoose.Schema({
    question: String,
    answer: String,
    options: [String],
    level: String,
    subject: String,
    language: String
});

const EngQuestion = mongoose.model('EngQuestion', questionSchema);
const CzQuestion = mongoose.model('CzQuestion', questionSchema);

app.get('/api/eng/questions', (req, res) => {
    EngQuestion.find().then(questions => { res.json(questions) });
});

app.get('/api/cz/questions', (req,res) => {
    CzQuestion.find().then(questions => { res.json(questions) });
});

app.get('/api/questions/:id', async (req, res) => {
    const id = String(req.params.id);
    try {
        let question = await EngQuestion.findById(id);
        if (!question) {
            question = await CzQuestion.findById(id);
            if(!question) {
                res.status(404).send('Question was not found at GET by id method.');
                return;
            }
        }
        res.json(question);
    } catch (err) {
        console.log(err);
    }
});

app.post('/api/create', (req, res) => {
    const { error } = validateQuestion(req.body, false);
    const language = req.body.language;
    if (error) {
        res.status(400).send(error.details[0].message);
    } else {
        if (language === 'English') {
            EngQuestion.create(req.body)
                .then(result => { res.json(result) })
                .catch(err => { res.send("Cannot save english question." + err) });
        } else if (language === 'Český') {
            CzQuestion.create(req.body)
                .then(result => { res.json(result) })
                .catch(err => { res.send("Cannot save czech question." + err) });
        } else {
            throw new Error('Language was not provided to POST method.');
        }
    }
});


app.put('/api/eng/edit/:id', async (req, res) => {
    const id = req.params.id;
    try {
        const { error } = validateQuestion(req.body, false);
        if (error) {
            console.log('Validation error: ' + error.details[0].message);
            res.status(400).send(error.details[0].message);
        } else {
            EngQuestion.findOneAndUpdate({ _id: id },
                {
                    $set: {
                        question: req.body.question,
                        answer: req.body.answer,
                        options: req.body.options,
                        level: req.body.level,
                        subject: req.body.subject
                    }
                }
                , { new: true }).then((docs) => {
                    if (docs) {
                        res.status(200).send(docs);
                    } else {
                        throw new Error('Cannot update english question');
                    }
                });
        }
    } catch (err) {
        console.log('Validation error : ' + err);
    }
});

app.put('/api/cz/edit/:id', async (req, res) => {
    const id = req.params.id;
    try {
        const { error } = validateQuestion(req.body, false);
        if (error) {
            console.log('Validation error: ' + error.details[0].message);
            res.status(400).send(error.details[0].message);
        } else {
            CzQuestion.findOneAndUpdate({ _id: id },
                {
                    $set: {
                        question: req.body.question,
                        answer: req.body.answer,
                        options: req.body.options,
                        level: req.body.level,
                        subject: req.body.subject
                    }
                }
                , { new: true }).then((docs) => {
                    if (docs) {
                        res.status(200).send(docs);
                    } else {
                        throw new Error('Cannot update czech question');
                    }
                });
        }
    } catch (err) {
        console.log('Validation error : ' + err);
    }
});

app.delete('/api/eng/delete/:id', async (req, res) => {
    const id = req.params.id;
    const result = await EngQuestion.findByIdAndDelete(id);
    res.send(result);
});

app.delete('/api/cz/delete/:id', async (req, res) => {
    const id = req.params.id;
    const result = await CzQuestion.findByIdAndDelete(id);
    res.send(result);
});


app.listen(port, () => console.log(`Listening on port ${port} ...`));

function validateQuestion(question, required = true) {
    const schema = Joi.object({
        _id: Joi.string(),
        __v: Joi.number(),
        question: Joi.string().min(4),
        answer: Joi.string().min(1),
        options: Joi.array().items(Joi.string()).min(1),
        subject: Joi.string().min(3),
        level: Joi.string().min(3),
        language: Joi.string().min(3)
    });

    return schema.validate(question, { presence: (required) ? "required" : "optional" });
}

