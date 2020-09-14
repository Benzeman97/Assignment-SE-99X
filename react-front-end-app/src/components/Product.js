import React from 'react';
import {faTrash} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';


function Product({product,value}) {

    const {productId,productName,numOfUnit,priceOfCartoon,urlOfImage,quantity}=product;
    const {increment,decrement,removeProduct,count}=value;


    return (
        <div className="row my-5 text-capitalize text-center">
            <div className="col-10 mx-auto col-lg-2">
                <img src={urlOfImage} style={{width:"5rem",height:"5rem"}}
                className="img-fluid" alt="product"/>
            </div>
            <div className="col-10 mx-auto col-lg-2">
                <span className="dg-lg-none"> product name : </span>
                {productName}
            </div>
            <div className="col-10 mx-auto col-lg-2">
                <span className="dg-lg-none">  price of cartoon : </span>
                     {priceOfCartoon}
            </div>
            <div className="col-10 mx-auto col-lg-2 my-2 my-lg-0">
                <div className="d-flex justify-content-center">
                    <div>
                        <span className="btn btn-black mx-1" onClick={()=>decrement(productId)}>
                           -
                        </span>
                        <span className="btn btn-black mx-1"> {quantity}</span>
                        <span className="btn btn-black mx-1" onClick={()=>increment(productId)}>
                           +
                        </span>

                    </div>

                </div>
            </div>
            {/*  */}
            <div className="col-10 mx-auto col-lg-2">
                <div className="product-icon" onClick={()=>removeProduct(productId)}>
                <FontAwesomeIcon className="trash" icon={faTrash}/>
                </div>
            </div>
            <div className="col-10 max-auto col-lg-2">
                <strong>Product Total : $ 0</strong>
            </div>
        </div>
    );
}

export default Product;