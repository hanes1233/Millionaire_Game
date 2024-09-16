import React, { useEffect, useState } from "react";
import { apiGet, apiDelete } from "../utils/api";
import { Table } from "../components/Table";

export function QuestionsTable() {
    const [engQuestions, setEngQuestions] = useState([]);
    const [czQuestions, setCzQuestion] = useState([]);

    useEffect(() => {
        apiGet("/api/eng/questions").then((data) => setEngQuestions(data));
        apiGet("/api/cz/questions").then((data) => setCzQuestion(data));
    }, []);

    const deleteQuestion = async (id, lang) => {
        try {
            if (lang === 'eng') {
                await apiDelete("/api/eng/delete/" + id);
                setEngQuestions(engQuestions.filter((item) => item._id !== id));
            } else if (lang === 'cz') {
                await apiDelete("/api/cz/delete/" + id);
                setCzQuestion(czQuestions.filter((item) => item._id !== id));
            } else {
                throw new Error('Wrong language input.');
            }
        } catch (error) {
            console.log(error.message);
            alert(error.message)
        }
    };


    if (!engQuestions || !czQuestions) {
        return (
            <div>
                <h3>Loading items...</h3>
            </div>
        )
    }


    return (
        <div className="row">
            <div className="col-6">
                <h3 className="text-primary">English questions</h3>
                <Table
                    language={'eng'}
                    questions={engQuestions}
                    deleteQuestion={deleteQuestion}
                />
            </div>
            <div className="col-6">
                <h3 className="text-secondary">Czech questions</h3>
                <Table
                    language={'cz'}
                    questions={czQuestions}
                    deleteQuestion={deleteQuestion}
                />
            </div>
        </div>
    )
}