import React from "react";

export function InputField(props) {

  const required = props.required || false;

  return (
    <div className="form-group px-5 my-3">
      <label>{props.label}:</label>
        <input
          required={required}
          className="form-control"
          placeholder={props.prompt}
          minLength={props.minlength}
          min={props.min}
          value={props.value}
          onChange={props.handleChange}
        />
    </div>
  );
}

export default InputField;