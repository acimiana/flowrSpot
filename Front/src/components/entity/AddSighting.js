import React, { useState, useCallback, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import AppAxios from '../../apis/AppAxios';
import { Row, Col, Form, Button } from "react-bootstrap"

const AddSighting = () => {
    const sighting = {

        "name": "",
        "description" : "",
        "latitude": 0,
        "longitude": 0,
        "flowerId": 0,
        "userId": 0

    };

    const [newSighting, setNewSighting] = useState(sighting);

    const [flowers, setFlowers] = useState([]);
    const [users, setUsers] = useState([]);

    var navigate = useNavigate();

    const getFlowers = useCallback(async (id) => {
        const response = await AppAxios.get('/flowers/id');
        setFlowers(response.data);
    }, []);

    const getUsers = useCallback(async (id) => {
        const response = await AppAxios.get('/users/id');
        setUsers(response.data);
    }, []);


    useEffect(() => {
        getFlowers();
        getUsers();
    }, []);

    const create = async () => {
        const body = {
            name: newSighting.name,
            description: newSighting.zaduzeni,
            latitude: newSighting.latitude,
            longitude: newSighting.longitude,
            flowerDTO: {
                id: newSighting.flowerId
            },
            userDTO: {
                id: newSighting.userId
            }
        };

        try {
            const response = await AppAxios.post('/sightings', body);
            navigate('/sightings');
        } catch (e) {
            alert(e);
        }
    }

    const onNameChange = (event) => {
        const value = event.target.value;
        const changedSighting = {
            name: value,
            description: newSighting.description,
            latitude: newSighting.latitude,
            longitude: newSighting.longitude,
            flowerId: newSighting.flowerId,
            userId: newSighting.userId
        }
        setNewSighting(changedSighting);
    }

    const onDescriptionChange = (event) => {
        const value = event.target.value;
        const changedSighting = {
            name: newSighting.name,
            description: value,
            latitude: newSighting.latitude,
            longitude: newSighting.longitude,
            flowerId: newSighting.flowerId,
            userId: newSighting.userId
        }
        setNewSighting(changedSighting);
    }

    const onLatitudeChange = (event) => {
        const value = event.target.value;
        const changedSighting = {
            name: newSighting.name,
            description: newSighting.description,
            latitude: value,
            longitude: newSighting.longitude,
            flowerId: newSighting.flowerId,
            userId: newSighting.userId
        }
        setNewSighting(changedSighting);
    }

    const onLongitudeChange = (event) => {
        const value = event.target.value;
        const changedSighting = {
            name: newSighting.name,
            description: newSighting.description,
            latitude: newSighting.latitude,
            longitude: value,
            flowerId: newSighting.flowerId,
            userId: newSighting.userId
        }
        setNewSighting(changedSighting);
    }

    const onFlowerChange = (event) => {
        const value = event.target.value;
        const changedSighting = {
            name: newSighting.name,
            description: newSighting.description,
            latitude: newSighting.latitude,
            longitude: newSighting.longitude,
            flowerId: value,
            userId: newSighting.userId
        }
        setNewSighting(changedSighting);
    }

    const onUserChange = (event) => {
        const value = event.target.value;
        const changedSighting = {
            name: newSighting.name,
            description: newSighting.description,
            latitude: newSighting.latitude,
            longitude: newSighting.longitude,
            flowerId: newSighting.flowerId,
            userId: value
        }
        setNewSighting(changedSighting);
    }

    const renderFlowers = () => {
        return flowers.map((flower)=> <option key={flower.id} value={flower.id}> {flower.ime}</option>)
    }

    const renderUsers = () => {
        return users.map((user)=> <option key={user.id} value={user.id}> {user.ime}</option>)
    }

    return (
        <div>
            <h1>Add sighting</h1>

            <Row className="justify-content-center">
                <Col md={6}>
                <Form>
                    <Form.Group>
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" onChange={(e)=> onNameChange(e)}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Description</Form.Label>
                        <Form.Control type="text" onChange={(e)=> onDescriptionChange(e)}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Latitude</Form.Label>
                        <Form.Control type="number" onChange={(e)=> onLatitudeChange(e)}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Longitude</Form.Label>
                        <Form.Control type="number" onChange={(e)=> onLongitudeChange(e)}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Flower</Form.Label>
                        <Form.Control as="select" name="flower" onChange={(e) => onFlowerChange(e)}>
                            <option>Choose a flower</option>
                            {renderFlowers()}
                        </Form.Control><br />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>User</Form.Label>
                        <Form.Control as="select" name="user" onChange={(e) => onUserChange(e)}>
                            <option>Choose a user</option>
                            {renderUsers()}
                        </Form.Control><br />
                    </Form.Group>


                </Form>

                <Button onClick={()=> create()}>Create</Button>

                </Col>
            </Row>

        </div>
    );
}

export default AddSighting;