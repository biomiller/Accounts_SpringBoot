import React from 'react';
import { Account } from './Account.js';


export function AccountList(props) {

    return (
        <div>
            <h1>Accounts:</h1>
            {props.data.map(account => (
                <div key={account.id}>
                    <br></br>
                    <Account
                        account={account}
                    />
                </div>
            ))
            }
        </div>

    );
}