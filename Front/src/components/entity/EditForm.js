import React, { useState, useCallback, useEffect } from 'react';
import AppAxios from '../../apis/AppAxios';
import { useNavigate, useParams } from 'react-router-dom';
import { Row, Col, Form, Button } from "react-bootstrap"

const EditSightingForm = () => {

    const urlParams = useParams();
    // console.log('urlParams', urlParams);

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
    const [updatedSighting, setUpdateSighting] = useState(updatedSighting);

    // const [zadatak, setZadatak] = useState(null); // OVAJ STATE SETUJE AXIOS KAD DOBAVI SA BE INICIJALNO ZADATAK

    const [flowers, setFlowers] = useState([]);
    const [users, setUsers] = useState([]);

    const getSightingById = useCallback(async (sightingId) => {
        const response = await AppAxios.get('/sightings/' + sightingId);
        console.log('Dobavio zadatak: ', response.data);
        setSighting(response.data);

        // .then(res => {
        //     // handle success
        //     console.log(res);
        //     setUpdateMovie({ movieId: res.data.id, movieName: res.data.naziv, movieDuration: res.data.trajanje});
        // })
        // .catch(error => {
        //     // handle error
        //     console.log(error);
        //     alert('Error occured please try again!');
        //  });
    }, []);

   useEffect(() => {
    getSightingById(sightingId)
   }, []);

    const edit = async () => {
        // var params = {
        //     'id': updateMovie.movieId,
        //     'naziv': updateMovie.movieName,
        //     'trajanje': updateMovie.movieDuration
        // };

        try {
            console.log('BODY KOJI ZELIM', zadatak); // zadatak je vec dobro namapiran, i ne treba nam params/body mapiranje, vec je u DTO obliku
            const response = await AppAxios.put('/sightings/' + sighting.id, sighting)
            alert('Update je uspesan');
            navigate('/sightings');
        } catch (e) {
            console.log('error se desio', e.response.data);
            alert('Neuspesan edit', e);
        }


        // .then(res => {
        //     // handle success
        //     console.log(res);
        //     alert('Movie was edited successfully!');
        //     navigate('/movies');
        // })
        // .catch(error => {
        //     // handle error
        //     console.log(error);
        //     alert('Error occured please try again!');
        //  });
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
        setUsers();
    }, []);

    const renderFlowers = () => {
        return flowers.map((flower)=> <option key={flower.id} value={flower.id}> {flower.ime}</option>)
    }

    const renderSprint = () => {
        return users.map((user)=> <option key={user.id} value={user.id}> {user.ime}</option>)
    }

    const onNameChange = (event) => {
        console.log("Nova vrednost imena", event.target.value);
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
        console.log("Nova vrednost imena", event.target.value);
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
        console.log("Nova vrednost imena", event.target.value);
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
    console.log("Nova vrednost imena", event.target.value);
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
    console.log("Nova vrednost imena", event.target.value);
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
    console.log("Nova vrednost imena", event.target.value);
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
                            <Form.Control as="select" name="flower" value={sighting.flowerDTO.id} onChange={(e) => onStanjeChange(e)}>
                                <option>Choose a flower</option>
                                {renderFlowers()}
                            </Form.Control><br />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>User</Form.Label>
                            <Form.Control as="select" name="user" value={sighting.userDTO.id} onChange={(e) => onSprintChange(e)}>
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

export default EditSightingForm;