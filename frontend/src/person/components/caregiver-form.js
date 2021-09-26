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

class CaregiverForm extends React.Component {

 constructor(props) {
        super(props);
        this.toggleForm = this.toggleForm.bind(this);
        this.reloadHandler = this.props.reloadHandler;
        this.onChangeBirthDate = this.onChangeBirthDate.bind(this);
        this.onChangeGender = this.onChangeGender.bind(this);

        this.state = {

            errorStatus: 0,
            error: null,

            formIsValid: false,
            birthDate: "",
            gender: '',

            formControls: {

                username: {
                                    value: '',
                                    placeholder: '',
                                    valid: false,
                                    touched: false,
                                    validationRules: {
                                        minLength: 3,
                                        isRequired: true
                                    }
                },

                email: {
                                    value: '',
                                    placeholder: '',
                                    valid: false,
                                    touched: false,
                                    validationRules: {
                                        emailValidator: true
                                    }
                                },

                password: {
                                    value: '',
                                    placeholder: '',
                                    valid: false,
                                    touched: false,
                                    validationRules: {
                                        minLength: 6,
                                        isRequired: true
                                    }
                                },

                name: {
                    value: '',
                    placeholder: '',
                    valid: false,
                    touched: false,
                    validationRules: {
                        minLength: 3,
                        isRequired: true
                    }
                },


                address: {
                    value: '',
                    placeholder: '',
                    valid: false,
                    touched: false,
                    validationRules: {
                                            minLength: 3,
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

    onChangeBirthDate(e) {
            this.setState({
              birthDate: e.target.value
            });
          }

       onChangeGender(e) {

               this.setState({
                 gender: e.target.value
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

    registerPerson(person) {
        return API_USERS.postCaregiver(person, (result, status, error) => {
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
        let person = {
            username: this.state.formControls.username.value,
            email: this.state.formControls.email.value,
            password: this.state.formControls.password.value,
            name: this.state.formControls.name.value,
            birthDate: this.state.birthDate,
            gender: this.state.gender,
            address: this.state.formControls.address.value,
            role: ["caregiver"]
        };

        console.log(person);
        this.registerPerson(person);
    }

    render() {
        return (
            <div>
                  <FormGroup id='username'>
                                <Label for='usernameField'> Username: </Label>
                                <Input name='username' id='usernameField' placeholder={this.state.formControls.username.placeholder}
                                       onChange={this.handleChange}
                                       defaultValue={this.state.formControls.username.value}
                                       touched={this.state.formControls.username.touched? 1 : 0}
                                       valid={this.state.formControls.username.valid}
                                       required
                                />
                                {this.state.formControls.username.touched && !this.state.formControls.username.valid &&
                                <div className={"error-message row"}> * Username must have at least 3 characters </div>}
                            </FormGroup>

               <FormGroup id='email'>
                                   <Label for='emailField'> Email: </Label>
                                   <Input name='email' id='emailField' placeholder={this.state.formControls.email.placeholder}
                                          onChange={this.handleChange}
                                          defaultValue={this.state.formControls.email.value}
                                          touched={this.state.formControls.email.touched? 1 : 0}
                                          valid={this.state.formControls.email.valid}
                                          required
                                   />
                                   {this.state.formControls.email.touched && !this.state.formControls.email.valid &&
                                   <div className={"error-message"}> * Email must have a valid format</div>}
                               </FormGroup>

                <FormGroup id='password'>
                                    <Label for='passwordField'> Password: </Label>
                                    <Input name='password' id='passwordField' placeholder={this.state.formControls.password.placeholder}
                                           onChange={this.handleChange}
                                           defaultValue={this.state.formControls.password.value}
                                           touched={this.state.formControls.password.touched? 1 : 0}
                                           valid={this.state.formControls.password.valid}
                                           required
                                    />
                                    {this.state.formControls.email.touched && !this.state.formControls.email.valid &&
                                    <div className={"error-message"}> * Password must have at least 3 characters</div>}
                                </FormGroup>

                <FormGroup id='name'>
                    <Label for='nameField'> Name: </Label>
                    <Input name='name' id='nameField' placeholder={this.state.formControls.name.placeholder}
                           onChange={this.handleChange}
                           defaultValue={this.state.formControls.name.value}
                           touched={this.state.formControls.name.touched? 1 : 0}
                           valid={this.state.formControls.name.valid}
                           required
                    />
                    {this.state.formControls.name.touched && !this.state.formControls.name.valid &&
                    <div className={"error-message row"}> * Name must have at least 3 characters </div>}
                </FormGroup>


               <div className="form-group">
                                                 <label htmlFor="birthDate">Birth Date</label>
                                                 <Input
                                                   type="date"
                                                   className="form-control"
                                                   name="birthDate"
                                                   value={this.state.birthDate}
                                                   onChange={this.onChangeBirthDate}
                                                   validations={[required]}
                                                 />
               </div>


                <FormGroup id='address'>
                                   <Label for='addressField'> Address: </Label>
                                   <Input name='address' id='addressField' placeholder={this.state.formControls.address.placeholder}
                                          onChange={this.handleChange}
                                          defaultValue={this.state.formControls.address.value}
                                          touched={this.state.formControls.address.touched? 1 : 0}
                                          valid={this.state.formControls.address.valid}
                                          required
                                   />
                               </FormGroup>








                <div className="radio">
                                                                   <label>
                                                                    <input
                                                                    type="radio"
                                                                     name="but"
                                                                     value='M'
                                                                     onChange={this.onChangeGender}
                                                                     />
                                                                     Male
                                                                     </label>
                </div>
                <div className="radio">
                                                                     <label>
                                                                     <input
                                                                     type="radio"
                                                                     name="but"
                                                                     value='F'
                                                                     onChange={this.onChangeGender}
                                                                     />
                                                                      Female
                                                                       </label>
                </div>




                    <Row>
                        <Col sm={{size: '4', offset: 8}}>
                            <Button type={"submit"} disabled={!this.state.formIsValid} onClick={this.handleSubmit}>  Submit </Button>
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

export default CaregiverForm;
