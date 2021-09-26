import React from "react";
import Table from "../../commons/tables/table";


const columns = [

    {
        Header: 'id',
        accessor: 'id',
    },
    {
        Header: 'id_patient',
        accessor: 'id_patient',
    },
];

const filters = [
    {
        accessor: 'name',
    }
];

class MedicalPlanTable1 extends React.Component {

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

export default MedicalPlanTable1;