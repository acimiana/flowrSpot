import React, { useState, useEffect, useCallback } from 'react';
import { Button, Col, Form, Row, Table } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import AppAxios from '../../apis/AppAxios';
import TableRow from './TableRow';

const FlowersTable = (props) => {

    const [flowers, setFlowers] = useState([]);

    const [search, setSearch] = useState(null);

    var navigate = useNavigate()

    const getFlowers = useCallback(async () => {
        const response = await AppAxios.get('/flowers/search', {
            params: {
                search: search,
              }
        });
        setFlowers(response.data);
    }, [search]);

    const deleteFlower = useCallback(async (id) => {
        try {
            const response = await AppAxios.delete('/flowers/' + id);
            alert('Deleted flower with id ', id, '!');
            window.location.reload();
        } catch (e) {
            alert('Error, delete unsuccesful!');
        }
    }, []);

    useEffect(() => {
        getFlowers();
    }, []);

    const renderRows = () => {
        return flowers.map((flower, index) => {
            return (
                <tr key={index}>
                    <td>{flower.name}</td>
                    <td>{flower.latinName}</td>
                    <td>{flower.sightingsNo}</td>
                    <td>{flower.profilePicture}</td>

                    <td>
                        <Button className="button button-navy" onClick={() => deleteFlower(flower.id)}>Obrisi</Button>
                    </td>

                </tr>
            )
         })
    }

    const goToAdd = () => {
        navigate('/flowers/add'); 
    }

    const onSearchHandler = (event) => {
        const value = event.target.value === '' ? null : event.target.value;
        setSearch(value);
    }

    return (
        <Col>
            <Row><h1>List</h1></Row>
            <Button className="button button-navy" onClick={() => goToAdd() }>Add</Button>
            <br/>

            <Row>
                <Form>
                    <Form.Group>
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" onChange={(e)=> onSearchHandler(e)}></Form.Control>
                    </Form.Group>

                    <Button className="button button-navy" onClick={() => getFlowers() }>Pretrazi</Button>
                </Form>
            </Row>

                
            <Row>
                <Col>
                    <Table id="flowers-table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Latin name</th>
                                <th>Number of sightings</th>
                                <th>Profile picture</th>
                            </tr>
                        </thead>
                        <tbody>
                            {renderRows()}
                        </tbody>                  
                    </Table>
                </Col>
            </Row>
        </Col>
    );
}

export default FlowersTable;