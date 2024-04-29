import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import AppAxios from "../../apis/AppAxios";


const TableRow = (props) => {

    var navigate = useNavigate()

    const deleteSighting = (sightingId) => {
        AppAxios.delete('/sightings/' + sightingId)
        .then(res => {
            console.log(res);
            alert('Sighting deleted succesfully!');
            window.location.reload();
        })
        .catch(error => {
            console.log(error);
            alert('Error occured, delete unsuccesful!');
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

           <td><Button className="button button-navy" onClick={() => deleteSighting(props.user.id)}>Delete</Button></td>
        </tr>
     )
}

export default TableRow;