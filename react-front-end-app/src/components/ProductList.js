import React from 'react';
import Product from './Product';
import TableModal from './Modal/TableModal';

function ProductList({value}) {
    const {products}=value;


    return (
        <div className="container-fluid">
         
        {
            products.map(product=>{
                return <Product key={product.productId} product={product} value={value}/>
            })
        }
                { value.modalOpen ? <TableModal/> : null}    
        </div>
    );
}

export default ProductList;