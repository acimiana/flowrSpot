import React, { useState, useCallback, useEffect } from 'react';
import AppAxios from '../../apis/AppAxios';
import { useNavigate, useParams } from 'react-router-dom';
import { Row, Col, Form, Button } from "react-bootstrap"

const EditFlower = () => {

    const urlParams = useParams();

    const flowerId = urlParams.id;

    var navigate = useNavigate()

    var flower = {

        flowerId: -1,
        flowerName: "",
        flowerLatinName: "",
        flowerSightingsNo: 0,
        flowerProfilePicture: ""

    }

    const [flower, setFlower] = useState(flower);

    const getFlowerById = useCallback(async (flowerId) => {
        const response = await AppAxios.get('/flowers/' + flowerId);
        setFlower(response.data);
    }, []);

   useEffect(() => {
    getFlowerById(flowerId)
   }, []);

    const edit = async () => {

        try {
            const response = await AppAxios.put('/flower/' + flower.id, flower)
            alert('Edit succesful!');
            navigate('/flowers');
        } catch (e) {
            alert('Edit unuccesful!', e);
        }
    }

    const onNameChange = (event) => {
        console.log("Nova vrednost imena", event.target.value);
        const value = event.target.value;
        const updatedFlower = {
            id: flower.id,
            name: value,
            latinName: flower.latinName,
            sightingsNo: flower.sightingsNo,
            profilePicture: flower.profilePicture
        }
        setFlower(updatedFlower);
    }

    const onLatinNameChange = (event) => {
        console.log("Nova vrednost imena", event.target.value);
        const value = event.target.value;
        const updatedFlower = {
            id: flower.id,
            name: flower.name,
            latinName: value,
            sightingsNo: flower.sightingsNo,
            profilePicture: flower.profilePicture
        }
        setFlower(updatedFlower);
    }

    const onSightingsNoChange = (event) => {
        console.log("Nova vrednost imena", event.target.value);
        const value = event.target.value;
        const updatedFlower = {
            id: flower.id,
            name: flower.name,
            latinName: flower.latinName,
            sightingsNo: value,
            profilePicture: flower.profilePicture
        }
        setFlower(updatedFlower);
    }

    const onProfilePictureChange = (event) => {
        console.log("Nova vrednost imena", event.target.value);
        const value = event.target.value;
        const updatedFlower = {
            id: flower.id,
            name: flower.name,
            latinName: flower.latinName,
            sightingsNo: flower.sightingsNo,
            profilePicture: value
        }
        setFlower(updatedFlower);
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
                            <Form.Control type="text" value={flower.name} onChange={(e)=> onNameChange(e)}></Form.Control>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Latin name</Form.Label>
                            <Form.Control type="text" value={flower.latinName} onChange={(e)=> onLatinNameChange(e)}></Form.Control>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Number of sightings</Form.Label>
                            <Form.Control type="number" value={flower.sightingsNo} onChange={(e)=> onSightingsNoChange(e)}></Form.Control>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Profile picture</Form.Label>
                            <Form.Control type="text" value={flower.profilePicture} onChange={(e)=> onProfilePictureChange(e)}></Form.Control>
                        </Form.Group>
                    </Form>

                    <Button onClick={()=> edit()}>Edit</Button>

                    </Col>
                </Row>
            )
            
        }

        { flower === null && (
                <h1>DATA LOADING</h1>
            )
        }            

    </div>
    );
}

export default EditFlower;