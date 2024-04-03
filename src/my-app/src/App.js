import './App.css';
import Container from './Container';
import Footer from './Footer';
import { getAllCustomers } from './client';
import AddCustomerForm from'./forms/AddCustomerForm';
import React, { Component } from 'react';
import{
  Avatar,
  Spin,
  Table,
  Modal
} from 'antd';
import { SmileOutlined } from '@ant-design/icons';

const getIndicatorIcon = () => <SmileOutlined type="loading" style={{fontSize:24}} spin />

class App extends Component {

  state = {
    customers:[],
    isFetching:false,
    isAddCustomerModalVisible: false
  }

  componentDidMount(){
    this.fetchCustomers();
  }

  openAddCustomerModal = () => this.setState({isAddCustomerModalVisible: true})

  closeAddCustomerModal = () => this.setState({isAddCustomerModalVisible: false})

  fetchCustomers = () => {
    this.setState({
      isFetching:true
    });
    getAllCustomers()
    .then(res => res.json())
    .then(customers =>{
      console.log(customers);
      this.setState({
        customers,
        isFetching: false
      });
    });
  }

  render() {

      const{ customers, isFetching } = this.state;

      if(isFetching){
        return (
          <Container>
            <Spin indicator = {getIndicatorIcon()}/>
          </Container>
        );
      }

      if(customers && customers.length) {
        
        const columns = [
          {
            title:'',
            key: 'avatar',
            render: (text, customer) =>(
              <Avatar size='large'>
                {`${customer.firstName.charAt(0).toUpperCase()}
                ${customer.lastName.charAt(0).toUpperCase()}`}
              </Avatar>
            )
          },
          {
            title: 'Customer Id',
            dataIndex: 'customerId',
            key:'customerId'
          },
          {
            title: 'First Name',
            dataIndex: 'firstName',
            key:'firstName'
          },
          {
            title: 'Last Name',
            dataIndex: 'lastName',
            key:'lastName'
          },
          {
            title: 'Email',
            dataIndex: 'email',
            key:'email'
          },
          {
            title: 'Gender',
            dataIndex: 'gender',
            key:'gender'
          }
        ];
        
        return (
          <Container>
            <Table 
              dataSource={customers} 
              columns={columns} 
              pagination={false}
              rowKey='customerId' />
            <Modal
              title='Add a new customer'
              open={this.state.isAddCustomerModalVisible}
              onOk={this.closeAddCustomerModal}
              onCancel={this.closeAddCustomerModal}
              width={1000}>
              <AddCustomerForm />
            </Modal>
            <Footer
              numberOfCustomers = {customers.length}
              handleAddCustomerClickEvent={this.openAddCustomerModal}
            />
          </Container>
          )
      }

      return <h1> No Customers found</h1>
    }
}

export default App;
