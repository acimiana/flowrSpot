import React, { useState, useEffect, useCallback } from 'react';
import { Button, Col, Form, Row, Table } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import AppAxios from '../../apis/AppAxios';
import TableRow from './TableRow';

const UserTable = (props) => {

    const [sightings, setSightings] = useState([]);
    const [sighting, setSighting] = useState([]);
    const [flowers, setFlowers] = useState([]);
    const [users, setUsers] = useState([]);

    var navigate = useNavigate()

    const getUser = useCallback(async (id) => {
        // const response = await AppAxios.get('/zadaci');
        const response = await AppAxios.get('/users/' + id, {
            params: {
                id: id,
              }
        });
        // console.log('RESPONSE OD AXIOSA', response.data);
        setFlowers(response.data);
    }, []);

    const getSightings = useCallback(async () => {
        // const response = await AppAxios.get('/zadaci');
        const response = await AppAxios.get('/sightings', {
        });
        // console.log('RESPONSE OD AXIOSA', response.data);
        setSightings(response.data);
    }, []);

    const getSighting = useCallback(async (id) => {
        // const response = await AppAxios.get('/zadaci');
        const response = await AppAxios.get('/sightings/' + id, {
        });
        // console.log('RESPONSE OD AXIOSA', response.data);
        setSighting(response.data);
    }, []);

    const deleteUser = useCallback(async (id) => {
        try {
            const response = await AppAxios.delete('/users/' + id);
            console.log('deleting result', response.data);
            alert('Deleted user with id ', id);
            window.location.reload();
        } catch (e) {
            console.log('GRESKA PRI BRISANJU', e);
            alert('GRESKA PRI BRISANJU');
        }
    }, []);

    const deleteSighting = useCallback(async (id) => {
        try {
            const response = await AppAxios.delete('/sightings/' + id);
            console.log('deleting result', response.data);
            alert('Deleted sighting with id ', id);
            window.location.reload();
        } catch (e) {
            console.log('GRESKA PRI BRISANJU', e);
            alert('GRESKA PRI BRISANJU');
        }
    }, []);

    // const goToEditPage = (id) => {
    //     navigate('/zadaci/edit/' + id);
    // };

    // const goToSledeceStanje = useCallback(async (id) => {
    //     try {
    //         await AppAxios.put('/zadaci/izmeni-stanje/' + id);
    //         alert('Presao u sledece stanje', id);
    //         getZadaci();
    //     } catch (e) {
    //         console.log('GRESKA PRI prelasku u sledece stanje', e);
    //         alert('GRESKA PRI prelasku u sledece stanje');
    //     }
    // }, []);

    useEffect(() => {
        // Prilikom ucitavanja stranice 
        // Potrebno je dobaviti SVE filmove
        // i zanrove koje cemo prikazati
        // u okviru select-a
        getUser();
        // getGenres();
    }, []);

    useEffect(() => {getSightings()})

    useEffect(() => {getSighting()})


    const renderUserRows = () => {
        return users.map((user, index) => {
            return (
                <tr key={index}>
                    <td>{user.firstName + user.lastName}</td>

                    <td>
                        {/* <Button className="button button-navy" disabled={pacijent.stanjeDTO.id === 3} onClick={() => goToSledeceStanje(zadatak.id)}>Predji na sledece stanje</Button> */}
                        {/* <Button className="button button-navy" onClick={() => goToEditPage(zadatak.id)}>Izmeni</Button> */}
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
                        {/* <Button className="button button-navy" disabled={pacijent.stanjeDTO.id === 3} onClick={() => goToSledeceStanje(zadatak.id)}>Predji na sledece stanje</Button> */}
                        {/* <Button className="button button-navy" onClick={() => goToEditPage(zadatak.id)}>Izmeni</Button> */}
                        <Button className="button button-navy" onClick={() => deleteUser(sighting.user.id)}>Delete</Button>
                    </td>

                </tr>
            )
         })
    }

    // const pretrazi = async () => {
    //     try {
    //         const response = await AppAxios.get('/zadaci/search', {
    //             params: {
    //                 imeZadatka: pretragaImeZadatka,
    //                 sprintId: pretragaSprintId
    //               }
    //         });
    //         console.log('response', response.data);
    //     } catch (e) {
    //         console.log('GRESKA PRI dobavljanju', e);
    //     }
    // }

    const handleChange = (e) => {
        const { name, value } = e.target;
        // ... -> spread operator u JS
        // Koristimo ga kada zelimo da napravimo plitku kopiju
        // vrednosti i smestimo ih u novi objekat
        // https://www.w3schools.com/react/react_es6_spread.asp
        // PRIMER:
        // ako je searchParams = { key1: "value1", key2: "value2" }
        // let a = { searchParams } => a = { searchParams: { key1: "value1", key2: "value2" }}
        // let b = { ...searchParams } => b = { key1: "value1", key2: "value2" }
        // Primecujemo razliku -> u a se dodaje atribut searchParams koji uzima vrednost objekta
        // dok se u b ti parovi kljuc/vrednost ubacuju "direktno" u b
        
        // Prilikom postavljanja parametara pretrage moramo da vodimo
        // Racuna da ne pregazimo prethodno unete vrednosti
        // Posto ova funkcija obradjuje promenu SVIH polja forme
        // Ukoliko ne bismo prosledili ...searchParams
        // Setovali bismo objekat koji ima samo jedan entry
        // name stavljamo u uglaste zagrade jer je to zapravo e.target.name
        // i ukoliko ga ne bismo stavili u uglaste zagrade
        // unutar searchParams objekta bi se nalazio samo name atribut
        // a ne ono sto nama treba, npr. genre, durationFrom, durationTo, ili name
        
        // setSearchParams({  ...searchParams, [name] : value})
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