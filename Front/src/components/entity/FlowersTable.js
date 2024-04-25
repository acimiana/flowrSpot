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
        // const response = await AppAxios.get('/zadaci');
        const response = await AppAxios.get('/flowers/search', {
            params: {
                search: search,
              }
        });
        // console.log('RESPONSE OD AXIOSA', response.data);
        setFlowers(response.data);
    }, [search]);

    const deleteFlower = useCallback(async (id) => {
        try {
            const response = await AppAxios.delete('/flowers/' + id);
            console.log('deleting result', response.data);
            alert('Deleted flower with id ', id);
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
        getFlowers();
        // getGenres();
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
                        {/* <Button className="button button-navy" disabled={pacijent.stanjeDTO.id === 3} onClick={() => goToSledeceStanje(zadatak.id)}>Predji na sledece stanje</Button> */}
                        {/* <Button className="button button-navy" onClick={() => goToEditPage(zadatak.id)}>Izmeni</Button> */}
                        <Button className="button button-navy" onClick={() => deleteFlower(flower.id)}>Obrisi</Button>
                    </td>

                </tr>
            )
         })
    }

    const goToAdd = () => {
        navigate('/flowers/add'); 
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

    const onSearchHandler = (event) => {
        const value = event.target.value === '' ? null : event.target.value;
        setSearch(value);
    }

    return (
        <Col>
            <Row><h1>Lista</h1></Row>
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