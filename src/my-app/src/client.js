import fetch from 'unfetch';

export const getAllCustomers = () => fetch('api/customer');