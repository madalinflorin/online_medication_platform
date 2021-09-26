import React from "react";
import Table from "../../commons/tables/table";


const columns = [
    {
        Header: 'Name',
        accessor: 'name',
    },
    {
        Header: 'Birth Date',
        accessor: 'birth_date',
    },

     {
            Header: 'Gender',
            accessor: 'gender',
        },

     {
            Header: 'Medical record',
            accessor: 'medical_record',
        },

        {
                    Header: 'User id',
                    accessor: 'id_user',
                },
                {
                                    Header: 'Email',
                                    accessor: 'email',
                                },
                                {
                                                    Header: 'Password',
                                                    accessor: 'password',
                                                },

     {
                         Header: 'Username',
                         accessor: 'username',
                     },
];

const filters = [
    {
        accessor: 'name',
    }
];

class PersonTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tableData: this.props.tableData
        };
    }

    render() {
        return (
            <Table
                data={this.state.tableData}
                columns={columns}
                search={filters}
                pageSize={5}
            />
        )
    }
}

export default PersonTable;