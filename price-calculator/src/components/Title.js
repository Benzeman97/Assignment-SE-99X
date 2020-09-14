import React from 'react';
import styled from 'styled-components';

function Title({name,title}) {
    return (
       <div className="row">
           <div className="col-10 mx-auto my-2 text-center text-title">
               
           <h1 className="text-capitalize font-weight-bold">
                         {name}
                         <Strong className="text-blue">
                                 {title}
                         </Strong>
                </h1>

           </div>
       </div>
    );
}

const Strong=styled.strong`
       margin-left:0.8rem;
`;

export default Title;