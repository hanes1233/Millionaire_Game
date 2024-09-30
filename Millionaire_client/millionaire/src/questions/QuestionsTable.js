import React, { useEffect, useState } from "react";
import { apiGet, apiDelete } from "../utils/api";
import { Table } from "../components/Table";
import { Filter } from "./Filter";

export function QuestionsTable() {
    const [engQuestions, setEngQuestions] = useState([]);
    const [czQuestions, setCzQuestion] = useState([]);
    const [filterCzState, setCzFilter] = useState({
        question: undefined,
        answer: undefined,
        option: undefined,
        level: undefined,
        subject: undefined,
        limit: undefined
    });

    const [filterEngState, setEngFilter] = useState({
        question: undefined,
        answer: undefined,
        options: undefined,
        level: undefined,
        subject: undefined,
        limit: undefined
    });

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

    const handleEngChange = async (e) => {
        if (e.target.value === "false" || e.target.value === "true" || e.target.value === '') {
            setEngFilter(prevState => {
                return { ...prevState, [e.target.name]: undefined }
            });
        } else {
            setEngFilter(prevState => {
                return { ...prevState, [e.target.name]: e.target.value }
            });
        }
        handleEngSubmit(e);
    };

    const handleCzChange = async (e) => {
        if (e.target.value === "false" || e.target.value === "true" || e.target.value === '') {
            setCzFilter(prevState => {
                return { ...prevState, [e.target.name]: undefined }
            });
        } else {
            setCzFilter(prevState => {
                return { ...prevState, [e.target.name]: e.target.value }
            });
        }
        handleCzSubmit(e);
    };

    const handleEngSubmit = async (e) => {
        e.preventDefault();
        const data = await apiGet("/api/eng/questions", filterEngState);
        setEngQuestions(data);
    };


    const handleCzSubmit = async (e) => {
        e.preventDefault();
        const data = await apiGet("/api/cz/questions", filterCzState);
        setCzQuestion(data);
    };




    if (!engQuestions || !czQuestions) {
        return (
            <div>
                <h3>Loading questions...</h3>
            </div>
        )
    }


    return (
        <div className="row">
            <div className="col-6">
                <Filter
                    handleChange={handleEngChange}
                    handleSubmit={handleEngSubmit}
                    filter={filterEngState}
                    confirm="Filter"
                />
                <Table
                    headingSkin={'text-primary'}
                    tableName={'English questions'}
                    itemsPerPage={10}
                    language={'eng'}
                    questions={engQuestions}
                    deleteQuestion={deleteQuestion}
                />
            </div>
            <div className="col-6">
                <Filter
                    handleChange={handleCzChange}
                    handleSubmit={handleCzSubmit}
                    filter={filterCzState}
                    confirm="Filter"
                />
                <Table
                    headingSkin={'text-secondary'}
                    tableName={'Czech questions'}
                    itemsPerPage={10}
                    language={'cz'}
                    questions={czQuestions}
                    deleteQuestion={deleteQuestion}
                />
            </div>
        </div>
    )
}