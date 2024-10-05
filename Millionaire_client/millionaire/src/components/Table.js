import { useState } from "react";
import { Link } from "react-router-dom";
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { PaginationControl } from 'react-bootstrap-pagination-control';
import '../style.css';

export function Table(props) {

    const questions = props.questions;
    const language = props.language;
    const deleteQuestion = props.deleteQuestion;
    const itemsPerPage = props.itemsPerPage;
    const [show, setShow] = useState(false);
    const [startOffSet, setStartOffset] = useState(0);
    const [endOffset, setEndOffSet] = useState(10);
    const [page, setPage] = useState(1)

    const slicedQuestions = questions.slice(startOffSet, endOffset);

    // Handling modal dialog
    const handleClose = () => setShow(false);
    const handleShow = () => {
        setShow(true);
    };

    // Invoke when user click to request another page.
    const handlePageClick = (event) => {
        setPage(event);
        // Reset to default page or set up next one
        if (event === 1) {
            setStartOffset(0);
            setEndOffSet(10);
        } else {
            let start = event * 10 - 10;
            setStartOffset(start);
            setEndOffSet(start + 10);
        }
    }

    // Set background color for 'level' column depending on difficulty
    function setLevelBackground(question) {
        if (question === 'Easy') {
            return <td className="bg-primary text-white">{question}</td>
        } else if (question === 'Medium') {
            return <td className="bg-warning text-white">{question}</td>
        } else if (question === 'Hard') {
            return <td className="bg-danger text-white">{question}</td>
        } else {
            return <td className="text-white" style={{ backgroundColor: "rgb(141, 0, 99)" }}>{question}</td>
        }
    }

    // Set background color for 'subject' column depending on subject
    function setSubjectBackground(subject) {
        if (subject === 'Geography') {
            return <td className="bg-success text-white">{subject}</td>
        } else if (subject === 'History') {
            return <td className="bg-info text-white">{subject}</td>
        } else {
            return <td className="bg-secondary text-white">{subject}</td>
        }
    }

    return (
        <div>
            <h3 className={props.headingSkin}>{props.tableName} : <strong>{props.quantity}</strong></h3>
            <table className="table table-bordered table-striped">
                <thead className="thead-dark">
                    <tr>
                        <th>#</th>
                        <th>Level</th>
                        <th>Subject</th>
                        <th>Question</th>
                        <th colSpan={2}>Methods</th>
                    </tr>
                </thead>
                <tbody>
                    {slicedQuestions.map((question, index) => (
                        <tr key={index + 1}>
                            <td>{index + 1}</td>
                            {setLevelBackground(question.level)}
                            {setSubjectBackground(question.subject)}
                            <td>{question.question}</td>
                            <td>
                                <div className="btn-group">
                                    <Link
                                        to={"/api/question/" + question._id}
                                        className="btn btn-sm btn-success mx-1"
                                    >
                                        GET
                                    </Link>
                                    <Link
                                        to={"/api/questions/" + question._id}
                                        className="btn btn-sm btn-warning mx-1 text-white"
                                    >
                                        PUT
                                    </Link>
                                    <button
                                        className="btn btn-sm btn-danger mx-1"
                                        onClick={() => {
                                            handleShow();
                                        }}
                                    >
                                        DELETE
                                    </button>
                                    <div>
                                        <Modal show={show} animation={true}>
                                            <Modal.Header className="headerbg border-dark" closeButton onClick={() => {
                                                handleClose();
                                            }}>
                                                <Modal.Title>Confirm delete</Modal.Title>
                                            </Modal.Header>
                                            <Modal.Body className="modalbg">Do you really want to <strong>delete</strong> this question?</Modal.Body>
                                            <Modal.Footer className="modalbg">
                                                <Button variant="secondary" onClick={() => {
                                                    handleClose();
                                                }}>
                                                    Close
                                                </Button>
                                                <Button variant="danger" onClick={() => {
                                                    deleteQuestion(question._id, language);
                                                    handleClose();
                                                }}>
                                                    Delete
                                                </Button>
                                            </Modal.Footer>
                                        </Modal>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    ))}

                </tbody>
            </table>
            <PaginationControl
                page={page}
                between={1}
                total={questions.length}
                limit={itemsPerPage}
                changePage={handlePageClick}
                ellipsis={1}
                next={true}
                last={true}
            />
        </div>
    )
}