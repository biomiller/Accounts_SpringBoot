import React from 'react';


export function Account(props) {

  return (
    <div>
        <h3>{props.account.firstName} {props.account.lastName}</h3>   
    </div>

  );
}