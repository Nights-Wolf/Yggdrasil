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
            adjustQuantity()
       } else {
            setItemCount(1)
            adjustQuantity()
       }
    }

    React.useEffect(async () => {
             await axios
                .get("http://localhost:8080/api/cart/items/" + props.id)
                .then(res => {
                        setItem(res.data)
                    })
                .catch(err => {
                        console.log(err.response)
                })
    }, [])




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

    const adjustQuantity = (quantity) => {

        axios
            .put("http://localhost:8080/api/cart/adjustQuantity/" + props.id + "/" + quantity)
            .then(res => {
                window.location.reload(false)
            })
            .catch(err => {
                console.log(err.response)
            })
    }

    return(
        <div className="cart-Card">
            <img src= {discount_picture_2} alt="zdjęcie produktu" />
            <div className="cart-details">
                <p className="name">{item.itemName}</p>
                <form>
                    <div className="item-quantity">
                        <button type="button" name="add" description="Kliknij aby dodać produkt do koszyka" onClick={() => adjustQuantity(1)} aira-label="Dodaj produkt" >+</button>
                        <input type="number" name="quantity"  value={itemCount} onChange={handleChange} aria-label="Liczba wybranych produktów" />
                        <button type="button" name="subtract" description="Kliknij aby odjąć produkt do koszyka" onClick={() => adjustQuantity(-1)} aria-label="Odejmij produkt" >-</button>
                        <button type="button" name="delete" description="Kliknij aby usunąć produkt do koszyka" onClick={deleteItem} aria-label="Usuń produkt z koszyka" ><i class="fa fa-trash fa-2x"></i></button>
                    </div>
                </form>
                <p className="price">{item.price * props.quantity} zł</p>
            </div>
        </div>
    )
}

export default Cart
