import React from "react";
import Table from "../../commons/tables/table";


const columns = [

    {
        Header: 'id',
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
                    Header: 'Start period tratament',
                    accessor: 'start_period_tratament',
                },
                {
                                    Header: 'End period tratament',
                                    accessor: 'end_period_tratament',
                                },
        {
                            Header: 'Intake interval start',
                            accessor: 'intake_interval_start',
                        },
                        {
                                            Header: 'Intake interval end',
                                            accessor: 'intake_interval_end',
                                        },
];

const filters = [
    {
        accessor: 'name',
    }
];

class MedicalPlanTable extends React.Component {

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

export default MedicalPlanTable;