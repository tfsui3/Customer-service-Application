import fetch from 'unfetch';

const checkStatus = response => {
    if(response.ok){
        return response;
    }else{
        let error = new Error(response.statusText);
        error.response = response;
        response.json().then(e =>{
            error.error = e;
        });
        return Promise.reject(error);
    }
}

export const getAllCustomers = () =>
    fetch('api/customer').then(checkStatus);


export const addNewCustomer = customer => 
    fetch('api/customer',{
        headers:{
            'Content-Type':'application/json'
        },
        method:'POST',
        body: JSON.stringify(customer)
    }
    )
    .then(checkStatus);