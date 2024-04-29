import React, { useState, useCallback, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import AppAxios from '../../apis/AppAxios';
import { Row, Col, Form, Button } from "react-bootstrap"

const AddFlower = () => {
    const flower = {

        "name": "",
        "latinName" : "",
        "sightingsNo": 0,
        "profilePicture": "",

    };

    const [newFlower, setNewFlower] = useState(flower);

    var navigate = useNavigate();

    const create = async () => {
        const body = {
            name: newFlower.name,
            latinName: newFlower.latinName,
            sightingsNo: newFlower.sightingsNo,
            profilePicture: newFlower.profilePicture,
        };

        try {
            const response = await AppAxios.post('/flowers', body);
            navigate('/flowers');
        } catch (e) {
            alert(e);
        }
    }

    const onNameChange = (event) => {
        const value = event.target.value;
        const changedFlower = {
            name: value,
            latinName: newFlower.latinName,
            sightingsNo: newFlower.sightingsNo,
            profilePicture: newFlower.profilePicture,
        }
        setNewFlower(changedFlower);
    }

    const onLatinNameChange = (event) => {
        const value = event.target.value;
        const changedFlower = {
            name: newFlower.name,
            latinName: value,
            sightingsNo: newFlower.sightingsNo,
            profilePicture: newFlower.profilePicture,
        }
        setNewFlower(changedFlower);
    }

    const onSightingsNoChange = (event) => {
        const value = event.target.value;
        const changedFlower = {
            name: newFlower.name,
            latinName: newFlower.latinName,
            sightingsNo: value,
            profilePicture: newFlower.profilePicture,
        }
        setNewFlower(changedFlower);
    }

    const onProfilePictureChange = (event) => {
        const value = event.target.value;
        const changedFlower = {
            name: value,
            latinName: newFlower.latinName,
            sightingsNo: newFlower.sightingsNo,
            profilePicture: value,
        }
        setNewFlower(changedFlower);
    }

    return (
        <div>
            <h1>Add flower</h1>

            <Row className="justify-content-center">
                <Col md={6}>
                <Form>
                    <Form.Group>
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" onChange={(e)=> onNameChange(e)}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Latin name</Form.Label>
                        <Form.Control type="text" onChange={(e)=> onLatinNameChange(e)}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Number of sightins</Form.Label>
                        <Form.Control type="number" onChange={(e)=> onSightingsNoChange(e)}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Profile picture</Form.Label>
                        <Form.Control type="text" onChange={(e)=> onProfilePictureChange(e)}></Form.Control>
                    </Form.Group>

                </Form>

                <Button onClick={()=> create()}>Create</Button>

                </Col>
            </Row>

        </div>
    );
}

export default AddFlower;