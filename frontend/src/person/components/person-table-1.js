import React from "react";
import Table from "../../commons/tables/table";


const columns = [
    {
        Header: 'Name',
        accessor: 'name',
        width : 250
    },
    {
        Header: 'Birth Date',
        accessor: 'birth_date',
        width : 110
    },

     {
            Header: 'Gender',
            accessor: 'gender',
        },

     {
            Header: 'Medical record',
            accessor: 'medical_record',
            width : 250
        },

        {
                    Header: 'User id',
                    accessor: 'id_user',
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