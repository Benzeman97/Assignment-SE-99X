import React from 'react';
import Product from './Product';

function ProductList({value}) {
    const {products}=value;

    console.log(products);
    products.forEach(prod => {
        console.log(products.productName);
    });

    return (
        <div className="container-fluid">
        {
            products.map(product=>{
                return <Product key={product.productId} product={product} value={value}/>
            })
        }
        </div>
    );
}

export default ProductList;