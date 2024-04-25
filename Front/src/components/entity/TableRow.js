import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import AppAxios from "../../apis/AppAxios";


const TableRow = (props) => {

    var navigate = useNavigate()

    const getGenresStringFromList = (list) => {
        return list.map(element => element.naziv).join(',');
    }

    // const goToEdit = (ZadatakId) => {
    //     navigate('/zadaci/edit/' + movieId); 
    // }

    const deletePacijent = (pacijentId) => {
        AppAxios.delete('/pacijenti/' + pacijentId)
        .then(res => {
            // handle success
            console.log(res);
            alert('Pacijent uspesno obrisan!');
            window.location.reload();
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    return (
        <tr>
           <td>{props.pacijent.ime}</td>
           <td>{props.pacijent.prezime}</td>
           <td>{props.pacijent.datumRodjenja}</td>
           <td>{props.pacijent.mestoRodjenja}</td>
           <td>{props.pacijent.Lbo}</td>
           <td>{props.pacijent.doktor.ime}</td>
           <td>{props.pacijent.doktor.prezime}</td>


           {/* <td>{getGenresStringFromList(props.movie.zanrovi)}</td> */}
           {/* <td><Button className="button button-navy" onClick={() => goToEdit(props.movie.id)}>Edit</Button></td> */}
           <td><Button className="button button-navy" onClick={() => deletePacijent(props.pacijent.id)}>Delete</Button></td>
        </tr>
     )
}

export default TableRow;