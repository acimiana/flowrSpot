import React, { useState, useCallback, useEffect } from 'react';
import AppAxios from '../../apis/AppAxios';
import { useNavigate, useParams } from 'react-router-dom';
import { Row, Col, Form, Button } from "react-bootstrap"

const EditSighting = () => {

    const urlParams = useParams();

    const sightingId = urlParams.id;

    var navigate = useNavigate()

    var sighting={

        sightingId: -1,
        sightingName: "",
        sightingDescription: 0,
        sightingLatitude,
        sightingLongitude
        
    }

    const [sighting, setSighting] = useState(sighting);

    const [flowers, setFlowers] = useState([]);
    const [users, setUsers] = useState([]);

    const getSightingById = useCallback(async (sightingId) => {
        const response = await AppAxios.get('/sightings/' + sightingId);
        console.log('Dobavio zadatak: ', response.data);
        setSighting(response.data);
    }, []);

   useEffect(() => {
    getSightingById(sightingId)
   }, []);

    const edit = async () => {

        try {
            const response = await AppAxios.put('/sightings/' + sighting.id, sighting)
            alert('Edit succesful!');
            navigate('/sightings');
        } catch (e) {
            alert('Edit unuccesful!', e);
        }
    }

    const getFlowers = useCallback(async () => {
        const response = await AppAxios.get('/flowers');
        setFlowers(response.data);
    }, []);

    const getUsers = useCallback(async () => {
        const response = await AppAxios.get('/users');
        setUsers(response.data);
    }, []);

    useEffect(() => {
        getFlowers();
        getUsers();
    }, []);

    const renderFlowers = () => {
        return flowers.map((flower)=> <option key={flower.id} value={flower.id}> {flower.ime}</option>)
    }

    const renderUsers = () => {
        return users.map((user)=> <option key={user.id} value={user.id}> {user.ime}</option>)
    }

    const onNameChange = (event) => {
        const value = event.target.value;
        const updatedSighting = {
            id: sighting.id,
            name: value,
            description: sighting.description,
            latitude: sighting.latitude,
            longitude: sighting.longitude,
            flowerDTO: sighting.flowerDTO,
            userDTO: sighting.userDTO
        }
        setSighting(updatedSighting);
    }

    const onDescriptionChange = (event) => {
        const value = event.target.value;
        const updatedSighting = {
            id: sighting.id,
            name: sighting.name,
            description: value,
            latitude: sighting.latitude,
            longitude: sighting.longitude,
            flowerDTO: sighting.flowerDTO,
            userDTO: sighting.userDTO
        }
            setSighting(updatedSighting);    
    }    

    const onLatitudeChange = (event) => {
        const value = event.target.value;
        const updatedSighting = {
            id: sighting.id,
            name: sighting.name,
            description: sighting.description,
            latitude: value,
            longitude: sighting.longitude,
            flowerDTO: sighting.flowerDTO,
            userDTO: sighting.userDTO
        }
                setSighting(updatedSighting);
    }
    const onLongitudeChange = (event) => {
    const value = event.target.value;
    const updatedSighting = {
        id: sighting.id,
         name: sighting.name,
        description: sighting.description,
        latitude: sighting.latitude,
        longitude: value,
        flowerDTO: sighting.flowerDTO,
        userDTO: sighting.userDTO
    }
                setSighting(updatedSighting);
    }
    
    const onFlowerChange = (event) => {
    const value = event.target.value;
    const updatedSighting = {
        id: sighting.id,
        name: sighting.name,
        description: sighting.description,
        latitude: sighting.latitude,
        longitude: sighting.longitude,
        flowerDTO: {
            id: value  
        },
        userDTO: sighting.userDTO
    }
            setSighting(updatedSighting);
    }

    const onUserChange = (event) => {
    const value = event.target.value;
    const updatedSighting = {
        id: sighting.id,
        name: sighting.name,
        description: sighting.description,
        latitude: sighting.latitude,
        longitude: sighting.longitude,
        flowerDTO: sighting.flowerDTO,
        userDTO: {
                id: value  
            }
        }
                setSighting(updatedSighting);
        }

    return (

        <div>

            <h1>Change sighting</h1>

            { sighting !== null && (
                <Row className="justify-content-center">
                    <Col md={6}>
                    <Form>
                        <Form.Group>
                            <Form.Label>Name</Form.Label>
                            <Form.Control type="text" value={sighting.ime} onChange={(e)=> onNameChange(e)}></Form.Control>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Description</Form.Label>
                            <Form.Control type="text" value={sighting.description} onChange={(e)=> onDescriptionChange(e)}></Form.Control>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Latitude</Form.Label>
                            <Form.Control type="number" value={sighting.latitude} onChange={(e)=> onLatitudeChange(e)}></Form.Control>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Longitude</Form.Label>
                            <Form.Control type="number" value={sighting.longitude} onChange={(e)=> onLongitudeChange(e)}></Form.Control>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Flower</Form.Label>
                            <Form.Control as="select" name="flower" value={sighting.flowerDTO.id} onChange={(e) => onFlowerChange(e)}>
                                <option>Choose a flower</option>
                                {renderFlowers()}
                            </Form.Control><br />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>User</Form.Label>
                            <Form.Control as="select" name="user" value={sighting.userDTO.id} onChange={(e) => onUserChange(e)}>
                                <option>Choose a user</option>
                                {renderUsers()}
                            </Form.Control><br />
                        </Form.Group>

                    </Form>

                    <Button onClick={()=> edit()}>Edit</Button>

                    </Col>
                </Row>
            )
            
        }

        { sighting === null && (
                <h1>DATA LOADING</h1>
            )
        }            

    </div>
    );
}

export default EditSighting;