import React from 'react';
import {ButtonContainer} from '../style-components/Button';


function ProductTotals({value}) {
    const {totalPrice}=value;

    return (
        <React.Fragment>
            <div className="container">
                <div className="row">
                    <div className="col-10 mt-2 ml-sm-5 ml-md-auto col-sm-8 text-capitalize text-right">
                       <h3>
                           <span className="text-title">
                               Total : <strong>${totalPrice.total}</strong>
                           </span>
                       </h3>
                           <ButtonContainer onClick={ value.modalOpen?()=>value.closeModal():()=>value.openModal()}>
                                   {value.modalOpen? <h5 className="mt-2">Close Table</h5>:
                                    <h5 className="mt-2">Show Cartoons</h5>}
                           </ButtonContainer>
                       
                    </div>
                </div>
            </div>
        </React.Fragment>
    );
} 

export default ProductTotals;