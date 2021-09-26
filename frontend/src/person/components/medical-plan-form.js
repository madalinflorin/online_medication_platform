import React from 'react';
import validate from "./validators/person-validators";
import Button from "react-bootstrap/Button";
import * as API_USERS from "../api/person-api";
import APIResponseErrorMessage from "../../commons/errorhandling/api-response-error-message";
import {Col, Row} from "reactstrap";
import { FormGroup, Input, Label} from 'reactstrap';

const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

class MedicalPlanForm extends React.Component {

 constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;
        this.onChangeDate = this.onChangeDate.bind(this);
        this.onChangeDate1 = this.onChangeDate1.bind(this);
        this.onChangeDate2 = this.onChangeDate2.bind(this);
        this.onChangeDate3 = this.onChangeDate3.bind(this);

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,
            start_period_tratament: "",
            end_period_tratament: "",
            intake_interval_start: "",
            intake_interval_end: "",
            name: "",

            formControls: {

                id: {
                    value: '',
                    placeholder: '',
                    valid: false,
                    touched: false,
                    validationRules: {
                        isRequired: true
                    }
                },



               id_medication: {
                                                   value: '',
                                                   placeholder: '',
                                                   valid: false,
                                                   touched: false,
                                                   validationRules: {
                                                       isRequired: true
                                                   }
                               },


            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    toggleForm() {
        this.setState({collapseForm: !this.state.collapseForm});
    }

    onChangeDate(e) {
            this.setState({
              start_period_tratament: e.target.value
            });
          }

    onChangeDate1(e) {
                this.setState({
                  end_period_tratament: e.target.value
                });
              }

     onChangeDate2(e) {
                    this.setState({
                      intake_interval_start: e.target.value
                    });
                  }

      onChangeDate3(e) {
                     this.setState({
                       intake_interval_end: e.target.value
                     });
                   }



    handleChange = event => {

        const name = event.target.name;
        const value = event.target.value;

        const updatedControls = this.state.formControls;

        const updatedFormElement = updatedControls[name];

        updatedFormElement.value = value;
        updatedFormElement.touched = true;
        updatedFormElement.valid = validate(value, updatedFormElement.validationRules);
        updatedControls[name] = updatedFormElement;

        let formIsValid = true;
        for (let updatedFormElementName in updatedControls) {
            formIsValid = updatedControls[updatedFormElementName].valid && formIsValid;
        }

        this.setState({
            formControls: updatedControls,
            formIsValid: formIsValid
        });

    };

    registerMedicalPlan(medical_plan) {
        return API_USERS.postMedicalPlan1(medical_plan, (result, status, error) => {
            if (result !== null && (status === 200 || status === 201)) {
                console.log("Successfully inserted patient with id: " + result);
                this.reloadHandler();
            } else {
                this.setState(({
                    errorStatus: status,
                    error: error
                }));
            }
        });
    }

    handleSubmit() {
        let medical_plan = {
            id : this.state.formControls.id.value,
            id_medication: this.state.formControls.id_medication.value,
            name : this.state.name,
            start_period_tratament: this.state.start_period_tratament,
            end_period_tratament: this.state.end_period_tratament,
            intake_interval_start: this.state.intake_interval_start,
            intake_interval_end: this.state.intake_interval_end
        };

        console.log(medical_plan);
        this.registerMedicalPlan(medical_plan);
    }

    render() {
        return (
            <div>

                <FormGroup id='id'>
                    <Label for='idField'> ID medical plan: </Label>
                    <Input name='id' id='idField' placeholder={this.state.formControls.id.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.id.value}
                           touched={this.state.formControls.id.touched? 1 : 0}
                           valid={this.state.formControls.id.valid}
                           required
                    />
                    {this.state.formControls.id.touched && !this.state.formControls.id.valid &&
                    <div className={"error-message"}> * ID must have a valid format</div>}
                </FormGroup>




               <FormGroup id='id_medication'>
                                   <Label for='id_medicationField'> ID medication: </Label>
                                   <Input name='id_medication' id='id_medicationField' placeholder={this.state.formControls.id_medication.placeholder}
                                          onChange={this.handleChange}
                                          defaultValue={this.state.formControls.id_medication.value}
                                          touched={this.state.formControls.id_medication.touched? 1 : 0}
                                          valid={this.state.formControls.id_medication.valid}
                                          required
                                   />
                                   {this.state.formControls.id_medication.touched && !this.state.formControls.id_medication.valid &&
                                   <div className={"error-message"}> * ID must have a valid format</div>}
                               </FormGroup>






               <div className="form-group">
                                                 <label htmlFor="start_period_tratament">Start period tratament</label>
                                                 <Input
                                                   type="date"
                                                   className="form-control"
                                                   name="start_period_tratament"
                                                   value={this.state.start_period_tratament}
                                                   onChange={this.onChangeDate}
                                                   validations={[required]}
                                                 />
               </div>


               <div className="form-group">
                                                                <label htmlFor="end_period_tratament">End period tratament</label>
                                                                <Input
                                                                  type="date"
                                                                  className="form-control"
                                                                  name="end_period_tratament"
                                                                  value={this.state.end_period_tratament}
                                                                  onChange={this.onChangeDate1}
                                                                  validations={[required]}
                                                                />
                              </div>

                <div className="form-group">
                                                                               <label htmlFor="intake_interval_start">Intake interval start</label>
                                                                               <Input
                                                                                 type="time"
                                                                                 className="form-control"
                                                                                 name="intake_interval_start"
                                                                                 value={this.state.intake_interval_start}
                                                                                 onChange={this.onChangeDate2}
                                                                                 validations={[required]}
                                                                               />
                                             </div>


                 <div className="form-group">
                                                                                <label htmlFor="intake_interval_end">Intake interval end</label>
                                                                                <Input
                                                                                  type="time"
                                                                                  className="form-control"
                                                                                  name="intake_interval_end"
                                                                                  value={this.state.intake_interval_end}
                                                                                  onChange={this.onChangeDate3}
                                                                                  validations={[required]}
                                                                                />
                                              </div>





                    <Row>
                        <Col sm={{size: '4', offset: 8}}>
                            <Button type={"submit"} disabled={!this.state.formIsValid} onClick={this.handleSubmit}> Submit medical plan </Button>
                        </Col>
                    </Row>

                {
                    this.state.errorStatus > 0 &&
                    <APIResponseErrorMessage errorStatus={this.state.errorStatus} error={this.state.error}/>
                }
            </div>
        ) ;
    }
}

export default MedicalPlanForm;
