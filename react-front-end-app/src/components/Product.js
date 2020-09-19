import React, { useEffect } from 'react';
import {faBookReader} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';


function Product({product,value}) {

    const {productId,productName,priceOfCartoon,urlOfImage,quantity,priceByUnit}=product;
    const {increment,decrement,description,removeProduct}=value;

    return (
        <div className="row my-5 text-capitalize text-center">
            <div className="col-10 mx-auto col-lg-2">
                <img src={urlOfImage} style={{width:"5rem",height:"5rem"}}
                className="img-fluid" alt="product"/>
            </div>
            <div className="col-10 mx-auto col-lg-2">
                <span className="dg-lg-none"> cartoon name : </span>
                {productName}
            </div>
            <div className="col-10 mx-auto col-lg-2">
                <span className="dg-lg-none">  price of cartoon : </span>
                     {priceOfCartoon}
            </div>
            <div className="col-10 mx-auto col-lg-2 my-2 my-lg-0">
                <div className="d-flex justify-content-center">
                    <div>
                        <span className="btn btn-black mx-1" onClick={()=>(quantity>0)?decrement(productId):null}>
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
                <div className="product-icon" onClick={()=>description(productId)}>
                <FontAwesomeIcon className="book-reader" icon={faBookReader}/>
                </div>
            </div>
            <div className="col-10 max-auto col-lg-2">
                <strong>Cartoon Total : $ {priceByUnit}</strong>
            </div>
        </div>
    );
}

export default Product;