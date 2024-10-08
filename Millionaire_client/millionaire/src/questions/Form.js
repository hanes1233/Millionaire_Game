import { useEffect, useState } from "react"
import Subject from "../constants/Subject";
import Difficulty from "../constants/Difficulty"
import InputField from "../components/InputField";
import { InputSelect } from "../components/InputSelect";
import { FlashMessage } from "../components/FlashMessage";
import { apiGet, apiPost, apiPut } from "../utils/api";
import { useParams } from "react-router-dom";
import Language from "../constants/Language";


export function Form() {

    const [sentState, setSent] = useState(false);
    const [successState, setSuccess] = useState(false);
    const [errorState, setError] = useState(null);
    const { id } = useParams();

    const [option1, setOption1] = useState('');
    const [option2, setOption2] = useState('');
    const [option3, setOption3] = useState('');
    const [option4, setOption4] = useState('');

    const sent = sentState;
    const success = successState;

    const [questionObj, setQuestion] = useState({
        question: "",
        answer: "",
        options: [],
        level: Difficulty.EASY,
        subject: Subject.GEOGRAPHY,
        language: Language.ENGLISH
    });

    useEffect(() => {
        if (id) {
            apiGet("/api/questions/" + id).then((data) => setQuestion(data));
        }
    }, [id]);


    const handleSubmit = (e) => {
        e.preventDefault();
        questionObj.options = [option1, option2, option3, option4];

        /*
        If id found - form is about updating existing row, otherwise about creating new
        Also we check language to use appropriate mapping address
        */
        if (id) {
            (questionObj.language === 'English' ? apiPut("/api/eng/edit/" + id, questionObj) : apiPut("/api/cz/edit/" + id, questionObj))
                .then(() => {
                    setSent(true);
                    setSuccess(true);
                })
                .catch((error) => {
                    console.log(error.message);
                    setError(error.message);
                    setSent(true);
                    setSuccess(false);
                });
        } else {
            apiPost("/api/create", questionObj)
                .then(() => {
                    setSent(true);
                    setSuccess(true);
                })
                .catch((error) => {
                    console.log(error.message);
                    setError(error.message);
                    setSent(true);
                    setSuccess(false);
                });
        }
    };

    return (
        <div className="container mt-5">
            <h4 className="text-center text-success">{id ? "Edit" : "Create"} question:</h4>
            {errorState ? (
                <div className="alert alert-danger">{errorState}</div>
            ) : null}
            {sent && (
                <FlashMessage
                    theme={success ? "success" : ""}
                    text={success ? "Successfully saved." : ""}
                />
            )}
            <form onSubmit={handleSubmit}>
                <InputSelect
                    values={Subject}
                    required={true}
                    label="Subject"
                    value={questionObj.subject}
                    prompt="Subject"
                    handleChange={(e) => {
                        setQuestion({ ...questionObj, subject: e.target.value });
                    }}
                />
                <InputSelect
                    values={Difficulty}
                    value={questionObj.level}
                    required={true}
                    label="Level"
                    prompt="Level"
                    handleChange={(e) => {
                        setQuestion({ ...questionObj, level: e.target.value });
                    }}
                />
                <InputField
                    required={true}
                    type="text"
                    value={questionObj.question}
                    min="3"
                    label="Question"
                    prompt="Question"
                    handleChange={(e) => {
                        setQuestion({ ...questionObj, question: e.target.value });
                    }}
                />
                <InputField
                    required={true}
                    type="text"
                    value={questionObj.answer}
                    min="3"
                    label="Answer"
                    prompt="Answer"
                    handleChange={(e) => {
                        setQuestion({ ...questionObj, answer: e.target.value });
                    }}
                />
                <InputSelect
                    disabled={id ? true : false}
                    values={Language}
                    required={true}
                    value={questionObj.language}
                    label="Language"
                    prompt="Language"
                    handleChange={(e) => {
                        setQuestion({ ...questionObj, language: e.target.value });
                    }}
                />
                <div className="row">
                    <div className="col-3">
                        <InputField
                            required={true}
                            type="text"
                            min="3"
                            label="Option 1"
                            prompt={questionObj.options[0]}
                            handleChange={(e) => {
                                setOption1(e.target.value)
                            }}
                        />
                    </div>
                    <div className="col-3">
                        <InputField
                            required={true}
                            type="text"
                            label="Option 2"
                            prompt={questionObj.options[1]}
                            handleChange={(e) => {
                                setOption2(e.target.value)
                            }}
                        />
                    </div>
                    <div className="col-3">
                        <InputField
                            required={true}
                            type="text"
                            label="Option 3"
                            prompt={questionObj.options[2]}
                            handleChange={(e) => {
                                setOption3(e.target.value)
                            }}
                        />
                    </div>
                    <div className="col-3">
                        <InputField
                            required={true}
                            type="text"
                            label="Option 4"
                            prompt={questionObj.options[3]}
                            handleChange={(e) => {
                                setOption4(e.target.value)
                            }}
                        />
                    </div>
                </div>
                <input type="submit" className="btn btn-primary m-5" value="Save" />
            </form>
        </div>
    )
}
