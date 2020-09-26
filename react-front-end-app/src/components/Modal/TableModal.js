import React from 'react';
import styled from 'styled-components';

function TableModal(props) {
    return (
            <TableContainer>
            <div className="container">
           <div className="row">
               <div id="modal" className="mx-auto col-lg-12 text-center text-capitalize p-5 mt-5">
                     <table className="table table-striped table-bordered">
                           
                              <thead>
                                  <tr>
                                         <th>Cartoon Id</th>
                                         <th>Cartoon Name</th>
                                         <th>Price Of Cartoon</th>
                                         <th>Units Per Cartoon</th>
                                        
                                       
                                  </tr>
                              </thead>

                              <tbody>

                                                
                                 <tr>
                                        <td>1001</td>
                                        <td>Penguin</td>
                                        <td>175.00</td>
                                        <td>20.00</td>
                                  </tr>
                                  <tr>
                                        <td>1002</td>
                                        <td>Tiger</td>
                                        <td>825.00</td>
                                        <td>5.00</td>
                                  </tr>


                              </tbody>

                              <tfoot>
                                           
                              <tr>
                                         <th>Cartoon Id</th>
                                         <th>Cartoon Name</th>
                                         <th>Price Of Cartoon</th>
                                         <th>Units Per Cartoon</th>
                                       
                                  </tr>
                              </tfoot>

                     </table>
               </div>
           </div>
            </div>
           
         </TableContainer>
    );
}

const TableContainer=styled.div`
position:fixed;
top:0;
left:0;
right:0;
bottom:0;
background: rgba(0,0,0,0.3);
display:flex;
align-items:center;
justify-center:center;
#modal{
    background: var(--mainWhite);
}



`;

export default TableModal;