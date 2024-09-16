import { useState, useEffect } from "react";
import { useParams } from "react-router-dom"
import { apiGet } from "../utils/api";

export function Question() {
    const { id } = useParams();
    const [question, setQuestion] = useState({});

    useEffect(() => {
        if (id) {
            apiGet("/api/questions/" + id).then((data) => setQuestion(data));
        }
    }, [id]);

    if (question.options === undefined) {
        return (<div>Loading...</div>);
    }

    return (
        <div className="container">
            <div className="card">
                <div className="card-body">
                    <div className="card-header text-primary">Question information</div>
                    <div className="row">
                        <div className="col-6">
                            <p className="my-5">Question:</p>
                            <p className="my-5">Answer:</p>
                            <p className="my-5">Options:
                            </p>
                            <p className="my-5">Level:</p>
                            <p className="my-5">Subject:</p>
                            <p className="my-5">Language:</p>
                        </div>
                        <div className="col-6">
                            <p className="my-5"><strong>{question.question}</strong></p>
                            <p className="my-5"><strong>{question.answer}</strong></p>
                            <p className="my-5">
                                <strong className="mx-1">{question.options[0]}</strong>,
                                <strong className="mx-1">{question.options[1]}</strong>,
                                <strong className="mx-1">{question.options[2]}</strong>,
                                <strong className="mx-1">{question.options[3]}</strong>
                            </p>
                            <p className="my-5"><strong>{question.level}</strong></p>
                            <p className="my-5"><strong>{question.subject}</strong></p>
                            <p className="my-5"><strong>{question.language}</strong></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}