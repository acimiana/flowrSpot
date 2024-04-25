import React, { useState, useCallback, useEffect } from 'react';
import AppAxios from '../../apis/AppAxios';
import { useNavigate, useParams } from 'react-router-dom';
import { Row, Col, Form, Button } from "react-bootstrap"

const EditForm = () => {

    const urlParams = useParams();
    // console.log('urlParams', urlParams);

    const zadatakId = urlParams.id;

    var navigate = useNavigate()

    var movie={
        movieId: -1,
        movieName: "",
        movieDuration: 0,
        movieGenres: []
    }

    const [updateMovie, setUpdateMovie] = useState(movie);

    const [zadatak, setZadatak] = useState(null); // OVAJ STATE SETUJE AXIOS KAD DOBAVI SA BE INICIJALNO ZADATAK

    const [stanja, setStanja] = useState([]);
    const [sprintovi, setSprintovi] = useState([]);

    const getZadatakById = useCallback(async (zadatakId) => {
        const response = await AppAxios.get('/zadaci/' + zadatakId);
        console.log('Dobavio zadatak: ', response.data);
        setZadatak(response.data);

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
     getZadatakById(zadatakId)
   }, []);

    const edit = async () => {
        // var params = {
        //     'id': updateMovie.movieId,
        //     'naziv': updateMovie.movieName,
        //     'trajanje': updateMovie.movieDuration
        // };

        try {
            console.log('BODY KOJI ZELIM', zadatak); // zadatak je vec dobro namapiran, i ne treba nam params/body mapiranje, vec je u DTO obliku
            const response = await AppAxios.put('/zadaci/' + zadatak.id, zadatak)
            alert('Update je uspesan');
            navigate('/zadaci');
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

    const getStanja = useCallback(async () => {
        const response = await AppAxios.get('/stanja');
        setStanja(response.data);
    }, []);

    const getSprintovi = useCallback(async () => {
        const response = await AppAxios.get('/sprintovi');
        setSprintovi(response.data);
    }, []);

    useEffect(() => {
        getStanja();
        getSprintovi();
    }, []);

    const renderStanja = () => {
        return stanja.map((stanje)=> <option key={stanje.id} value={stanje.id}> {stanje.ime}</option>)
    }

    const renderSprint = () => {
        return sprintovi.map((sprint)=> <option key={sprint.id} value={sprint.id}> {sprint.ime}</option>)
    }

    const onNameChange = (event) => {
        console.log("Nova vrednost imena", event.target.value);
        const value = event.target.value;
        const zadatakPromenjen = {
            id: zadatak.id,
            ime: value,
            zaduzeni: zadatak.zaduzeni,
            bodovi: zadatak.bodovi,
            stanjeDTO: zadatak.stanjeDTO,
            sprintDTO: zadatak.sprintDTO
        }
        setZadatak(zadatakPromenjen);
    }

    const onZaduzeniChange = (event) => {
        console.log("Nova vrednost zaduzenog", event.target.value);
        const value = event.target.value;
        const zadatakPromenjen = {
            id: zadatak.id,
            ime: zadatak.ime,
            zaduzeni: value,
            bodovi: zadatak.bodovi,
            stanjeDTO: zadatak.stanjeDTO,
            sprintDTO: zadatak.sprintDTO
        }
        setZadatak(zadatakPromenjen);
    }

    const onBodoviChange = (event) => {
        console.log("Nova vrednost bodova", event.target.value);
        const value = event.target.value;
        const zadatakPromenjen = {
            id: zadatak.id,
            ime: zadatak.ime,
            zaduzeni: zadatak.zaduzeni,
            bodovi: value,
            stanjeDTO: zadatak.stanjeDTO,
            sprintDTO: zadatak.sprintDTO
        }
        setZadatak(zadatakPromenjen);
    }

    const onStanjeChange = (event) => {
        console.log("Nova vrednost stanja", event.target.value);
        const value = event.target.value;
        const zadatakPromenjen = {
            id: zadatak.id,
            ime: zadatak.ime,
            zaduzeni: zadatak.zaduzeni,
            bodovi: zadatak.bodovi,
            stanjeDTO: {
                id: value  
            },
            sprintDTO: zadatak.sprintDTO
        }
        setZadatak(zadatakPromenjen);
    }

    const onSprintChange = (event) => {
        console.log("Nova vrednost sprinta", event.target.value);
        const value = event.target.value;
        const zadatakPromenjen = {
            id: zadatak.id,
            ime: zadatak.ime,
            zaduzeni: zadatak.zaduzeni,
            bodovi: zadatak.bodovi,
            sprintDTO: {
                id: value  
            },
            stanjeDTO: zadatak.stanjeDTO
        }
        setZadatak(zadatakPromenjen);
    }

    return (

        <div>

            <h1>Izmeni Zadatak</h1>

            { zadatak !== null && (
                <Row className="justify-content-center">
                    <Col md={6}>
                    <Form>
                        <Form.Group>
                            <Form.Label>Ime</Form.Label>
                            <Form.Control type="text" value={zadatak.ime} onChange={(e)=> onNameChange(e)}></Form.Control>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Zaduzeni</Form.Label>
                            <Form.Control type="text" value={zadatak.zaduzeni} onChange={(e)=> onZaduzeniChange(e)}></Form.Control>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Bodovi</Form.Label>
                            <Form.Control type="number" value={zadatak.bodovi} onChange={(e)=> onBodoviChange(e)}></Form.Control>
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Stanje</Form.Label>
                            <Form.Control as="select" name="stanje" value={zadatak.stanjeDTO.id} onChange={(e) => onStanjeChange(e)}>
                                <option>Izaberi stanje</option>
                                {renderStanja()}
                            </Form.Control><br />
                        </Form.Group>

                        <Form.Group>
                            <Form.Label>Sprint</Form.Label>
                            <Form.Control as="select" name="sprint" value={zadatak.sprintDTO.id} onChange={(e) => onSprintChange(e)}>
                                <option>Izaberi sprint</option>
                                {renderSprint()}
                            </Form.Control><br />
                        </Form.Group>


                    </Form>

                    <Button onClick={()=> edit()}>Edit</Button>

                    </Col>
                </Row>
            )
            
        }

        { zadatak === null && (
                <h1>DATA LOADING</h1>
            )
        }

            

    </div>
    );
}

export default EditForm;