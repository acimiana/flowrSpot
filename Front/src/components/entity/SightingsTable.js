import React, { useState, useEffect, useCallback } from 'react';
import { Button, Col, Form, Row, Table } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import AppAxios from '../../apis/AppAxios';
import TableRow from './TableRow';

const SightingsTable = (props) => {

    const [sightings, setSightings] = useState([]) // state za filmove koje prikazujemo u tabeli
    const [flowers, setFlowers] = useState([]) // state za zanrove koje prikazujemo u select-u
    const [user, setUsers] = useState([]) // state za zanrove koje prikazujemo u select-u

    var navigate = useNavigate()

    const getSightings = useCallback(() => {
        AppAxios.get('/sightings')
            .then(res => {
                 // handle success
                 console.log(res);
                 setSightings(res.data)
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }, []);

    const getFlowers = () => {
        AppAxios.get('/flowers')
        .then(res => {
            setFlowers(res.data);
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
        });
    }

    const getUsers = () => {
        AppAxios.get('/users')
        .then(res => {
            setUsers(res.data);
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
        });
    }

    useEffect(() => {
        // Prilikom ucitavanja stranice 
        // Potrebno je dobaviti SVE filmove
        // i zanrove koje cemo prikazati
        // u okviru select-a
        getSightings();
        getFlowers();
        getUsers();
    }, []);

    const renderRows = () => {
        return sightings.map((sighting, index) => {
            return <TableRow key={sighting.id} sighting={sighting}></TableRow>
         })
    }

    function goToAdd() {
        navigate('/sightings/add'); 
    }

    return (
        <Col>
            <Row><h1>Lista</h1></Row>
                <Button className="button button-navy" onClick={() => goToAdd() }>Add</Button>
                <br/>
                
                <Row><Col>
                <Table id="sightings-table">
                    <thead>
                        <tr>
                            
                            <th>Flower</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>User</th>

                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderRows()}
                    </tbody>                  
                </Table>
                </Col></Row>
        </Col>
    );
}

export default SightingsTable;