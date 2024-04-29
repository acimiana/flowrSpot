import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import AppAxios from "../../apis/AppAxios";


const TableRow = (props) => {

    var navigate = useNavigate()

    // const getGenresStringFromList = (list) => {
    //     return list.map(element => element.naziv).join(',');
    // }

    // const goToEdit = (ZadatakId) => {
    //     navigate('/zadaci/edit/' + movieId); 
    // }

    const deleteSighting = (sightingId) => {
        AppAxios.delete('/sightings/' + sightingId)
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
           <td>{props.sighting.name}</td>
           <td>{props.sighting.description}</td>
           <td>{props.sighting.latitude}</td>
           <td>{props.sighting.longitude}</td>
           <td>{props.sighting.user.firstName}</td>
           <td>{props.sighting.user.lastName}</td>

           {/* <td>{getGenresStringFromList(props.movie.zanrovi)}</td> */}
           {/* <td><Button className="button button-navy" onClick={() => goToEdit(props.movie.id)}>Edit</Button></td> */}
           <td><Button className="button button-navy" onClick={() => deleteSighting(props.user.id)}>Delete</Button></td>
        </tr>
     )
}

export default TableRow;