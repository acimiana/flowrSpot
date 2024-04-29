import React, { useState, useEffect, useCallback } from 'react';
import { Button, Col, Row, Table } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import AppAxios from '../../apis/AppAxios';

const UserTable = (props) => {

    const [sightings, setSightings] = useState([]);
    const [sighting, setSighting] = useState([]);
    const [flowers, setFlowers] = useState([]);
    const [users, setUsers] = useState([]);

    var navigate = useNavigate()

    const getUser = useCallback(async (id) => {
        const response = await AppAxios.get('/users/' + id, {
            params: {
                id: id,
              }
        });
        setFlowers(response.data);
    }, []);

    const getSightings = useCallback(async () => {
        const response = await AppAxios.get('/sightings', {
        });
        setSightings(response.data);
    }, []);

    const getSighting = useCallback(async (id) => {
        const response = await AppAxios.get('/sightings/' + id, {
        });
        setSighting(response.data);
    }, []);

    const deleteUser = useCallback(async (id) => {
        try {
            const response = await AppAxios.delete('/users/' + id);
            alert('Deleted user with id ', id, '!');
            window.location.reload();
        } catch (e) {
            alert('Error, delete unsuccesful!');
        }
    }, []);

    const deleteSighting = useCallback(async (id) => {
        try {
            const response = await AppAxios.delete('/sightings/' + id);
            alert('Deleted sighting with id ', id, '!');
            window.location.reload();
        } catch (e) {
            alert('Error, delete unsuccesful!');
        }
    }, []);

    useEffect(() => {
        getUser();
    }, []);

    useEffect(() => {getSightings()})

    useEffect(() => {getSighting()})


    const renderUserRows = () => {
        return users.map((user, index) => {
            return (
                <tr key={index}>
                    <td>{user.firstName + user.lastName}</td>

                    <td>
                        <Button className="button button-navy" onClick={() => deleteUser(user.id)}>Delete</Button>
                    </td>

                </tr>
            )
         })
    }

    const renderSightingsRows = () => {
        return sightings.map((sighting, index) => {
            return (
                <tr key={index}>

                    <td>{sighting.flower.profilePicture}</td>

                    <td>{sighting.flower.name + 'by' + sighting.user.firstName + sighting.user.lastName}</td>

                    <td>{sighting.description}</td>

                    <td>
                        <Button className="button button-navy" onClick={() => deleteSighting(sighting.id)}>Delete</Button>
                    </td>

                </tr>
            )
         })
    }

    return (
        <colgroup>
            <Col>                
                <Row>
                    <Col>
                        <Table id="user-table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                </tr>
                            </thead>
                            <tbody>
                                {renderUserRows()}
                            </tbody>                  
                        </Table>
                    </Col>
                </Row>
            </Col>

            <Col>                
                <Row>
                    <Col>
                        <Table id="sightings-table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                </tr>
                            </thead>
                            <tbody>
                                {renderSightingsRows()}
                            </tbody>                  
                        </Table>
                    </Col>
                </Row>
            </Col>
        </colgroup>
    );
}

export default UserTable;