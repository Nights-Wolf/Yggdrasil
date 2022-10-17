import React from "react";
import axios from "axios";
import discount_picture_2 from "./assets/images/promotion_image_2.jpg";
import { confirmAlert } from 'react-confirm-alert';

function Cart(props) {

   const [itemCount, setItemCount] = React.useState(props.quantity)
   const [item, setItem] = React.useState([{}])

   function handleChange(event) {
        setItemCount(event.target.value)
   }

    function adjustCount(amount) {
       if (itemCount || itemCount != 0) {
            setItemCount(amount + parseInt(itemCount, 10))
       } else {
            setItemCount(1)
       }
    }

    if(props.item != undefined) {
    React.useEffect(async () => {
             await axios
                .get("http://localhost:8080/api/item/all/" + props.item)
                .then(res => {
                        setItem(res.data)
                    })
                .catch(err => {
                        console.log(err.response)
                })
    }, [])
}



   const deleteItem = () => {
    confirmAlert({
         message: 'Czy usunąć pozycje z koszyka?',
         buttons: [
           {
             label: 'Tak',
             onClick: () => axios
                                        .delete("http://localhost:8080/api/cart/cartItem/" + item.id)
                                        .then(res => {
                                            window.location.reload(false)
                                        })
                                        .catch(err => {
                                            console.log(err.response)
                                        })
           },
           {
             label: 'Nie',
             onClick: () =>{}
           }
         ]
       });
     };

    return(
        <div className="cart-Card">
            <img src= {discount_picture_2} />
            <div className="cart-details">
                <p className="name">{item.itemName}</p>
                <form>
                    <div className="item-quantity">
                        <button type="button" name="add" onClick={() => adjustCount(1)} >+</button>
                        <input type="number" name="quantity"  value={itemCount} onChange={handleChange} />
                        <button type="button" name="subtract" onClick={() => adjustCount(-1)} >-</button>
                        <button type="button" name="delete" onClick={deleteItem} ><i class="fa fa-trash fa-2x"></i></button>
                    </div>
                </form>
                <p className="price">{item.price * props.quantity} zł</p>
            </div>
        </div>
    )
}

export default Cart