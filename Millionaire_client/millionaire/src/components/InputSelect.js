
export function InputSelect(props) {

    const label = props.label;
    const subjectArr = [];
    const value = props.value;

    Object.values(props.values).map((el) => {
        subjectArr.push(el);
        return subjectArr;
    });

    return (
        <div className="px-5 my-3">
            <label>{props.label}:</label>
            <select
                disabled={props.disabled}
                value={value}
                className="browser-default form-select"
                label={label}
                values={subjectArr}
                readOnly={true}
                onChange={props.handleChange}
            >
                {subjectArr.map((value, index) => (
                    <option key={index} value={value}>
                        {value}
                    </option>
                ))}
            </select>
        </div>
        );
  }