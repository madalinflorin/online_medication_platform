import React from "react";
import Table from "../../commons/tables/table";


const columns = [
    {
        Header: 'Id',
        accessor: 'id',
    },
    {
        Header: 'Name',
        accessor: 'name',
    },

     {
            Header: 'Dosage',
            accessor: 'dosage',
        },

        {
                    Header: 'List of effects',
                    accessor: 'list_of_effects',
                },
];

const filters = [
    {
        accessor: 'name',
    }
];

class MedicationTable extends React.Component {

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

export default MedicationTable;