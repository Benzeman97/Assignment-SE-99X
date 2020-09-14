import React from 'react';

function ProductTotals({value}) {
    const {productTotal}=value;

    return (
        <React.Fragment>
            <div className="container">
                <div className="row">
                    <div className="col-10 mt-2 ml-sm-5 ml-md-auto col-sm-8 text-capitalize text-right">
                       <h3>
                           <span className="text-title">
                               Total : <strong>${productTotal}</strong>
                           </span>
                       </h3>
                    </div>
                </div>
            </div>
        </React.Fragment>
    );
}

export default ProductTotals;