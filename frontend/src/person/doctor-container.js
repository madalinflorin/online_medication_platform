import React from 'react';
import { Link } from "react-router-dom";
import {
    Button,
    Card,
    CardHeader,
    Col,
    Modal,
    ModalBody,
    ModalHeader,
    Row
} from 'reactstrap';
import PersonForm from "./components/person-form";
import CaregiverForm from "./components/caregiver-form";
import MedicationForm from "./components/medication-form";
import SelectPacientForm from "./components/select-pacient-form";
import SelectCaregiverForm from "./components/select-caregiver-form";
import SelectMedicationForm from "./components/select-medication-form";
import DeletePacientForm from "./components/delete-pacient-form";
import DeleteMedicationForm from "./components/delete-medication-form";
import DeleteCaregiverForm from "./components/delete-caregiver-form";
import PersonUpdateForm from "./components/person-insert-form";
import CaregiverUpdateForm from "./components/caregiver-update-form";
import MedicationUpdateForm from "./components/medication-update-form";
import CaregiverPatientForm from "./components/caregiver-patient-form";
import MedicalPlanForm from "./components/medical-plan-form";
import MedicalPlanForm1 from "./components/medical-plan-form1";
import * as API_USERS from "./api/person-api"




class DoctorContainer extends React.Component {

    constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.toggleForm1 = this.toggleForm1.bind(this);
        this.toggleForm2 = this.toggleForm2.bind(this);
        this.toggleForm3 = this.toggleForm3.bind(this);
        this.toggleForm4 = this.toggleForm4.bind(this);
        this.toggleForm5 = this.toggleForm5.bind(this);
        this.toggleForm6 = this.toggleForm6.bind(this);
        this.toggleForm7 = this.toggleForm7.bind(this);
        this.toggleForm8 = this.toggleForm8.bind(this);
        this.toggleForm9 = this.toggleForm9.bind(this);
        this.toggleForm10 = this.toggleForm10.bind(this);
        this.toggleForm11 = this.toggleForm11.bind(this);
        this.toggleForm12 = this.toggleForm12.bind(this);
        this.toggleForm13 = this.toggleForm13.bind(this);
        this.toggleForm14 = this.toggleForm14.bind(this);
        this.toggleForm15 = this.toggleForm15.bind(this);
        this.reload = this.reload.bind(this);
        this.reload1 = this.reload1.bind(this);
        this.reload2 = this.reload2.bind(this);
        this.reload3 = this.reload3.bind(this);
        this.reload4 = this.reload4.bind(this);
        this.reload5 = this.reload5.bind(this);
        this.reload6 = this.reload6.bind(this);
        this.reload7 = this.reload7.bind(this);
        this.reload8 = this.reload8.bind(this);
        this.reload9 = this.reload9.bind(this);
        this.reload10 = this.reload10.bind(this);
        this.reload11 = this.reload11.bind(this);
        this.reload12 = this.reload12.bind(this);
        this.reload13 = this.reload13.bind(this);
        this.reload14 = this.reload14.bind(this);
        this.reload15 = this.reload15.bind(this);
        this.state = {
            selected: false,
            selected1: false,
            selected2: false,
            selected3: false,
            selected4: false,
            selected5: false,
            selected6: false,
            selected7: false,
            selected8: false,
            selected9: false,
            selected10: false,
            selected11: false,
            selected12: false,
            selected13: false,
            selected14: false,
            selected15: false,
            collapseForm: false,
            tableData: [],
            isLoaded: false,
            errorStatus: 0,
            error: null
        };
    }

    componentDidMount() {
        this.fetchPersons();
    }

    fetchPersons() {
        return API_USERS.getPatients((result, status, err) => {

            if (result !== null && status === 200) {
                this.setState({
                    tableData: result,
                    isLoaded: true
                });
            } else {
                this.setState(({
                    errorStatus: status,
                    error: err
                }));
            }
        });
    }

    toggleForm() {
        this.setState({selected: !this.state.selected});
    }

    toggleForm1() {
            this.setState({selected1: !this.state.selected1});
        }

    toggleForm2() {
            this.setState({selected2: !this.state.selected2});
        }

    toggleForm3() {
            this.setState({selected3: !this.state.selected3});
        }

    toggleForm4() {
                this.setState({selected4: !this.state.selected4});
            }

    toggleForm5() {
            this.setState({selected5: !this.state.selected5});
        }

    toggleForm6() {
            this.setState({selected6: !this.state.selected6});
        }
    toggleForm7() {
            this.setState({selected7: !this.state.selected7});
        }
     toggleForm8() {
                this.setState({selected8: !this.state.selected8});
            }


    toggleForm9() {
            this.setState({selected9: !this.state.selected9});
        }
    toggleForm10() {
             this.setState({selected10: !this.state.selected10});
         }
    toggleForm11() {
            this.setState({selected11: !this.state.selected11});
        }
    toggleForm12() {
             this.setState({selected12: !this.state.selected12});
         }

     toggleForm13() {
                 this.setState({selected13: !this.state.selected13});
             }

     toggleForm14() {
                      this.setState({selected14: !this.state.selected14});
                  }

    toggleForm15() {
        this.setState({selected15: !this.state.selected15});
    }

    reload() {
        this.setState({
            isLoaded: false
        });
        this.toggleForm();
        this.fetchPersons();
    }

    reload1(){
    this.setState({
                isLoaded: false
            });
    this.toggleForm1();
    }

    reload2(){

    this.setState({
                    isLoaded: false
                });
    this.toggleForm2();
    }

    reload3(){
    this.setState({
                    isLoaded: false
                });
    this.toggleForm3();
    }

    reload4(){
        this.setState({
                        isLoaded: false
                    });
        this.toggleForm4();
        }

    reload5(){
        this.setState({
                        isLoaded: false
                    });
        this.toggleForm5();
        }

    reload6(){
        this.setState({
                        isLoaded: false
                    });
        this.toggleForm6();
        }

    reload7(){
        this.setState({
                        isLoaded: false
                    });
        this.toggleForm7();
        }

    reload8(){
        this.setState({
                        isLoaded: false
                    });
        this.toggleForm8();
        }

    reload9(){
        this.setState({
                        isLoaded: false
                    });
        this.toggleForm9();
        }

    reload10(){
        this.setState({
                        isLoaded: false
                    });
        this.toggleForm10();
        }


    reload11(){
        this.setState({
                        isLoaded: false
                    });
        this.toggleForm11();
        }


    reload12(){
        this.setState({
                        isLoaded: false
                    });
        this.toggleForm12();
        }

    reload13(){
            this.setState({
                            isLoaded: false
                        });
            this.toggleForm13();
            }

    reload14(){
            this.setState({
                            isLoaded: false
                        });
            this.toggleForm14();
            }

    reload15(){
        this.setState({
            isLoaded: false
        });
        this.toggleForm15();
    }

    render() {
        return (
            <div>
                <CardHeader>
                    <strong> Doctor Management </strong>
                </CardHeader>
                <Card>
                    <br/>
                    <Row>

                        <Col sm={{size: '8', offset: 1}}>
                            <Button color="primary" onClick={this.toggleForm1}>Add Patient </Button>
&nbsp;
                            <Button color="primary" onClick={this.toggleForm4}>Select Patient </Button>
                            &nbsp;
                            <Button color="primary" onClick={this.toggleForm7}>Edit Patient </Button>
                            &nbsp;

                            <Button color="primary" onClick={this.toggleForm}>Delete Patient </Button>

                         &nbsp;





                        </Col>



                    </Row>
 <br/>
  <br/>
                    <Row>

                                            <Col sm={{size: '8', offset: 1}}>
                                                <Button color="primary" onClick={this.toggleForm2}>Add Caregiver </Button>
&nbsp;
                                                <Button color="primary" onClick={this.toggleForm5}>Select Caregiver </Button>
                                               &nbsp; <Button color="primary" onClick={this.toggleForm8}>Edit Caregiver </Button>
                                                &nbsp;     <Button color="primary" onClick={this.toggleForm11}>Delete Caregiver </Button>
                                             <br/><br/>

                                            </Col>



                                        </Row>
                    <br/>

                     <br/>
                                        <Row>

                                                                <Col sm={{size: '8', offset: 1}}>
                                                                    <Button color="primary" onClick={this.toggleForm3}>Add Medication </Button>
&nbsp;
                                                                   <Button color="primary" onClick={this.toggleForm6}>Select Medication </Button>
                                                               &nbsp;     <Button color="primary" onClick={this.toggleForm9}>Edit medication </Button>


                                                                &nbsp;    <Button color="primary" onClick={this.toggleForm12}>Delete medication </Button>
</Col>

<br/>                     <br/>

<br/>                     <br/>

<Col sm={{size: '8', offset: 1}}>
<Button color="secondary"><Link to="/doctor/list/pacients">List all pacients</Link></Button>
 &nbsp;
<Button color="secondary"><Link to="/doctor/list/caregivers">List all caregivers</Link></Button>
&nbsp;
<Button color="secondary"><Link to="/doctor/list/medications">List all medications</Link></Button>
    &nbsp;
    <Button color="secondary"><Link to="/doctor/list/medical_plans">List all medical plans</Link></Button>

                                                                </Col>

</Row>

 <br/>                     <br/>

<Row>
<Col sm={{size: '8', offset: 1}}>
<Button color="primary" onClick={this.toggleForm13}>Add caregiver-patient </Button>
&nbsp;
<Button color="primary" onClick={this.toggleForm14}>Add medical plan </Button>
&nbsp;
<Button color="primary" onClick={this.toggleForm15}>Add medications to medical plan </Button>
  </Col>
</Row>

                </Card>

                <Modal isOpen={this.state.selected1} toggle={this.toggleForm1}
                       className={"Patient"} size="lg">
                    <ModalHeader toggle={this.toggleForm1}> Add Patient: </ModalHeader>
                    <ModalBody>
                        <PersonForm reloadHandler={this.reload1}/>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.selected2} toggle={this.toggleForm2}
                                       className={"Caregiver"} size="lg">
                                    <ModalHeader toggle={this.toggleForm2}> Add caregiver: </ModalHeader>
                                    <ModalBody>
                                        <CaregiverForm reloadHandler={this.reload2}/>
                                    </ModalBody>
                                </Modal>


                <Modal isOpen={this.state.selected3} toggle={this.toggleForm3}
                                       className={"Medication"} size="lg">
                                    <ModalHeader toggle={this.toggleForm3}> Add medication: </ModalHeader>
                                    <ModalBody>
                                        <MedicationForm reloadHandler={this.reload3}/>
                                    </ModalBody>
                                </Modal>

                <Modal isOpen={this.state.selected4} toggle={this.toggleForm4}
                                                       className={"Patient1"} size="lg">
                                                    <ModalHeader toggle={this.toggleForm4}> Select patient: </ModalHeader>
                                                    <ModalBody>
                                                        <SelectPacientForm reloadHandler={this.reload4}/>
                                                    </ModalBody>
                                                </Modal>


                <Modal isOpen={this.state.selected5} toggle={this.toggleForm5}
                                                       className={"Caregiver1"} size="lg">
                                                    <ModalHeader toggle={this.toggleForm5}> Select Caregiver: </ModalHeader>
                                                    <ModalBody>
                                                        <SelectCaregiverForm reloadHandler={this.reload5}/>
                                                    </ModalBody>
                                                </Modal>




                <Modal isOpen={this.state.selected6} toggle={this.toggleForm6}
                                                       className={"Medication1"} size="lg">
                                                    <ModalHeader toggle={this.toggleForm6}> Select medication: </ModalHeader>
                                                    <ModalBody>
                                                        <SelectMedicationForm reloadHandler={this.reload6}/>
                                                    </ModalBody>
                                                </Modal>

                <Modal isOpen={this.state.selected} toggle={this.toggleForm}
                                                                       className={"Delete patient"} size="lg">
                                                                    <ModalHeader toggle={this.toggleForm}> Delete patient: </ModalHeader>
                                                                    <ModalBody>
                                                                        <DeletePacientForm reloadHandler={this.reload}/>
                                                                    </ModalBody>
                                                                </Modal>
                 <Modal isOpen={this.state.selected11} toggle={this.toggleForm11}
                                                                                       className={"Delete caregiver"} size="lg">
                                                                                    <ModalHeader toggle={this.toggleForm11}> Delete caregiver: </ModalHeader>
                                                                                    <ModalBody>
                                                                                        <DeleteCaregiverForm reloadHandler={this.reload11}/>
                                                                                    </ModalBody>
                                                                                </Modal>

                  <Modal isOpen={this.state.selected12} toggle={this.toggleForm12}
                                                                                        className={"Delete medication"} size="lg">
                                                                                     <ModalHeader toggle={this.toggleForm12}> Delete medication: </ModalHeader>
                                                                                     <ModalBody>
                                                                                         <DeleteMedicationForm reloadHandler={this.reload12}/>
                                                                                     </ModalBody>
                                                                                 </Modal>



                  <Modal isOpen={this.state.selected7} toggle={this.toggleForm7}
                                                                                                          className={"Update patient"} size="lg">
                                                                                                       <ModalHeader toggle={this.toggleForm7}> Update patient: </ModalHeader>
                                                                                                       <ModalBody>
                                                                                                           <PersonUpdateForm reloadHandler={this.reload7}/>
                                                                                                       </ModalBody>
                                                                                                   </Modal>


                  <Modal isOpen={this.state.selected8} toggle={this.toggleForm8}
                                                                                                                            className={"Update caregiver"} size="lg">
                                                                                                                         <ModalHeader toggle={this.toggleForm8}> Update caregiver: </ModalHeader>
                                                                                                                         <ModalBody>
                                                                                                                             <CaregiverUpdateForm reloadHandler={this.reload8}/>
                                                                                                                         </ModalBody>
                                                                                                                     </Modal>

                  <Modal isOpen={this.state.selected9} toggle={this.toggleForm9}
                                                                                                                                                                 className={"Update medication"} size="lg">
                                                                                                                                                              <ModalHeader toggle={this.toggleForm9}> Update medication: </ModalHeader>
                                                                                                                                                              <ModalBody>
                                                                                                                                                                  <MedicationUpdateForm reloadHandler={this.reload9}/>
                                                                                                                                                              </ModalBody>
                                                                                                                                                          </Modal>

                  <Modal isOpen={this.state.selected13} toggle={this.toggleForm13}
                                                                                                                                                                                   className={"Add caregiver-patient"} size="lg">
                                                                                                                                                                                <ModalHeader toggle={this.toggleForm13}> Add caregiver-patient: </ModalHeader>
                                                                                                                                                                                <ModalBody>
                                                                                                                                                                                    <CaregiverPatientForm reloadHandler={this.reload13}/>
                                                                                                                                                                                </ModalBody>
                                                                                                                                                                            </Modal>

                   <Modal isOpen={this.state.selected14} toggle={this.toggleForm14}
                                                                                                                                                                                                     className={"Add medical plan"} size="lg">
                                                                                                                                                                                                  <ModalHeader toggle={this.toggleForm14}> Add medical plan: </ModalHeader>
                                                                                                                                                                                                  <ModalBody>
                                                                                                                                                                                                      <MedicalPlanForm1 reloadHandler={this.reload14}/>
                                                                                                                                                                                                  </ModalBody>
                                                                                                                                                                                              </Modal>

                <Modal isOpen={this.state.selected15} toggle={this.toggleForm15}
                       className={"Add medical plan"} size="lg">
                    <ModalHeader toggle={this.toggleForm15}> Add medications to medical plan: </ModalHeader>
                    <ModalBody>
                        <MedicalPlanForm reloadHandler={this.reload15}/>
                    </ModalBody>
                </Modal>
            </div>
        )

    }
}


export default DoctorContainer;
