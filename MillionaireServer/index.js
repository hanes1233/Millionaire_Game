const express = require('express');
var bodyParser = require('body-parser');
const Joi = require('joi');
const app = express();
const port = 5000;
const mongoose = require('mongoose');
app.use(express.json());
app.use(bodyParser.json());

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

questionSchema.query.byQuestion = function (questionText) {
    return this.where({ question: new RegExp(questionText, 'i') });
}

questionSchema.query.byAnswer = function (answerText) {
    return this.where({ answer: new RegExp(answerText, 'i') })
}

questionSchema.query.bySubject = function (subjectText) {
    return this.where({ subject: new RegExp(subjectText, 'i') })
}

questionSchema.query.byLevel = function (levelText) {
    return this.where({ level: new RegExp(levelText, 'i') })
}

questionSchema.query.byOption = function (option) {
    return this.where({ options: new RegExp(option, 'i') })
}

const EngQuestion = mongoose.model('EngQuestion', questionSchema);
const CzQuestion = mongoose.model('CzQuestion', questionSchema);

app.get('/api/eng/questions', async (req, res) => {
    EngQuestion.find()
        .byQuestion(req.query.question)
        .byAnswer(req.query.answer)
        .byOption(req.query.options)
        .bySubject(req.query.subject)
        .byLevel(req.query.level)
        .limit(req.query.limit)
        .then(ques => { res.json(ques) });
});

app.get('/api/cz/questions', async (req, res) => {
    CzQuestion.find()
        .byQuestion(req.query.question)
        .byAnswer(req.query.answer)
        .byOption(req.query.options)
        .bySubject(req.query.subject)
        .byLevel(req.query.level)
        .limit(req.query.limit)
        .then(ques => { res.json(ques) });
});

app.get('/api/questions/:id', async (req, res) => {
    const id = String(req.params.id);
    try {
        let question = await EngQuestion.findById(id);
        if (!question) {
            question = await CzQuestion.findById(id);
            if (!question) {
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

