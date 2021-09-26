import React from "react";
import Table from "../../commons/tables/table";


const columns = [
    {
        Header: 'Name',
        accessor: 'name',
    },
    {
        Header: 'Activity',
        accessor: 'activity',
    },

     {
            Header: 'Start activity date',
            accessor: 'start_date_activity',
            width: 250
     },

     {
            Header: 'End activity date',
            accessor: 'end_date_activity',
            width: 250
        },

        {
                    Header: 'Warning message',
                    accessor: 'warningMessage',
                    width: 300

                }
];

const filters = [
    {
        accessor: 'name'
    }
];


class PersonActivityTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            tableData: this.props.tableData
        };
    }

    render() {
        return (
            <Table style={{ width: 1200 }}
                data={this.state.tableData}
                columns={columns}
                search={filters}
                pageSize={5}
            />
        )
    }
}

export default PersonActivityTable;