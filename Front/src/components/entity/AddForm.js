import React, { useState, useCallback, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import AppAxios from '../../apis/AppAxios';
import { Row, Col, Form, Button } from "react-bootstrap"

const AddForm = () => {

    const zadatak = {
        "ime": "",
        "zaduzeni": "",
        "bodovi": 0,
        "sprintId": 0,
        "stanjeId": 0
    };

    const [newZadatak, setNewZadatak] = useState(zadatak);

    const [stanja, setStanja] = useState([]);
    const [sprintovi, setSprintovi] = useState([]);

    var navigate = useNavigate();

    const getStanja = useCallback(async () => {
        const response = await AppAxios.get('/stanja');
        console.log('RESPONSE stanja OD AXIOSA', response.data);
        setStanja(response.data);
    }, []);

    const getSprintovi = useCallback(async () => {
        const response = await AppAxios.get('/sprintovi');
        console.log('RESPONSE sprintova OD AXIOSA', response.data);
        setSprintovi(response.data);
    }, []);


    useEffect(() => {
        getStanja();
        getSprintovi();
    }, []);

    const create = async () => {
        const body = {
            ime: newZadatak.ime,
            zaduzeni: newZadatak.zaduzeni,
            bodovi: newZadatak.bodovi,
            sprintDTO: {
                id: newZadatak.sprintId
            },
            stanjeDTO: {
                id: newZadatak.stanjeId
            }
        };

        console.log("OVO JE BODY", body);

        try {
            const response = await AppAxios.post('/zadaci', body);
            console.log('response za create', response);
            alert('Zadatak uspesno kreiran');
            navigate('/zadaci');
        } catch (e) {
            console.log('DESIO SE ERROR', e);
            alert(e);
        }

        // .then(res => {
        //     // handle success
        //     console.log(res);
           
        //     alert('Movie was added successfully!');
        //     navigate('/movies'); 
        // })
        // .catch(error => {
        //     // handle error
        //     console.log(error);
        //     alert('Error occured please try again!');
        //  });
    }

    const onNameChange = (event) => {
        console.log("Nova vrednost imena", event.target.value);
        const value = event.target.value;
        const zadatakPromenjen = {
            ime: value,
            zaduzeni: newZadatak.zaduzeni,
            bodovi: newZadatak.bodovi,
            stanjeId: newZadatak.stanjeId,
            sprintId: newZadatak.sprintId
        }
        setNewZadatak(zadatakPromenjen);
    }

    const onZaduzeniChange = (event) => {
        console.log("Nova vrednost zaduzenog", event.target.value);
        const value = event.target.value;
        const zadatakPromenjen = {
            ime: newZadatak.ime,
            zaduzeni: value,
            bodovi: newZadatak.bodovi,
            stanjeId: newZadatak.stanjeId,
            sprintId: newZadatak.sprintId
        }
        setNewZadatak(zadatakPromenjen);
    }

    const onBodoviChange = (event) => {
        console.log("Nova vrednost bodova", event.target.value);
        const value = event.target.value;
        const zadatakPromenjen = {
            ime: newZadatak.ime,
            zaduzeni: newZadatak.zaduzeni,
            bodovi: value,
            stanjeId: newZadatak.stanjeId,
            sprintId: newZadatak.sprintId
        }
        setNewZadatak(zadatakPromenjen);
    }

    const onStanjeChange = (event) => {
        console.log("Nova vrednost stanja", event.target.value);
        const value = event.target.value;
        const zadatakPromenjen = {
            ime: newZadatak.ime,
            zaduzeni: newZadatak.zaduzeni,
            bodovi: newZadatak.bodovi,
            stanjeId: value,
            sprintId: newZadatak.sprintId
        }
        setNewZadatak(zadatakPromenjen);
    }

    const onSprintChange = (event) => {
        console.log("Nova vrednost sprinta", event.target.value);
        const value = event.target.value;
        const zadatakPromenjen = {
            ime: newZadatak.ime,
            zaduzeni: newZadatak.zaduzeni,
            bodovi: newZadatak.bodovi,
            sprintId: value,
            stanjeId: newZadatak.stanjeId
        }
        setNewZadatak(zadatakPromenjen);
    }

    const renderStanja = () => {
        return stanja.map((stanje)=> <option key={stanje.id} value={stanje.id}> {stanje.ime}</option>)
    }

    const renderSprint = () => {
        return sprintovi.map((sprint)=> <option key={sprint.id} value={sprint.id}> {sprint.ime}</option>)
    }

    return (
        <div>
            <h1>Add Zadatak</h1>
            {/* <label htmlFor="ime">Ime</label>
            <input id="ime" type="text" onChange={(e) => onNameChange(e)}/><br/>

            <label htmlFor="zaduzeni">Zaduzeni</label>
            <input id="zaduzeni" type="text" onChange={(e) => onDurationChange(e)}/>

            <label htmlFor="bodovi">Bodovi</label>
            <input id="bodovi" type="number" onChange={(e) => onDurationChange(e)}/>
            
            <button className="button button-navy" onClick={create}>Add</button> */}

            <Row className="justify-content-center">
                <Col md={6}>
                <Form>
                    <Form.Group>
                        <Form.Label>Ime</Form.Label>
                        <Form.Control type="text" onChange={(e)=> onNameChange(e)}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Zaduzeni</Form.Label>
                        <Form.Control type="text" onChange={(e)=> onZaduzeniChange(e)}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Bodovi</Form.Label>
                        <Form.Control type="number" onChange={(e)=> onBodoviChange(e)}></Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Stanje</Form.Label>
                        <Form.Control as="select" name="stanje" onChange={(e) => onStanjeChange(e)}>
                            <option>Izaberi stanje</option>
                            {renderStanja()}
                        </Form.Control><br />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Sprint</Form.Label>
                        <Form.Control as="select" name="sprint" onChange={(e) => onSprintChange(e)}>
                            <option>Izaberi sprint</option>
                            {renderSprint()}
                        </Form.Control><br />
                    </Form.Group>


                </Form>

                <Button onClick={()=> create()}>Create</Button>

                </Col>
            </Row>


        </div>
    );
}

export default AddForm;