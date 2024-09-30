import React from "react";

export function InputField(props) {

  const required = props.required || false;

  return (
    <div className="form-group px-5 my-3">
      <label>{props.label}:</label>
        <input
          name={props.name}
          type={props.type}
          required={required}
          className="form-control"
          placeholder={props.prompt}
          minLength={props.minlength}
          min={props.min}
          max={props.max}
          value={props.value}
          onChange={props.handleChange}
        />
    </div>
  );
}

export default InputField;