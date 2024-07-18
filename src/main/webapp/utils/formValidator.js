/**
 * formValidator.js
 * Validates a form by taking the form id and checking all the formGroups inside it.
 * If the form is valid it submits the form.
 * @param formSelector
 */
const validateForm = formSelector => {
    //Takes the form by the id
    const formElement = document.querySelector(formSelector);

    /**
     * A list of validation options that must be added into the tag you want to validate
     * If a tag is required, add the required attribute, if it has a minimum length, add the minlength attribute and so on
     *
     */
    const validationOptions = [
        {
            attribute: 'match',
            isValid: input => {
                const matchSelector = input.getAttribute('match');
                const matchedElement = formElement.querySelector(`#${matchSelector}`);
                return matchedElement && matchedElement.value.trim() === input.value.trim();
            },
            errorMessage: (input, label) => {
                const matchSelector = input.getAttribute('match');
                const matchedElement = formElement.querySelector(`#${matchSelector}`);
                const matchedLabel = matchedElement.parentElement.parentElement.querySelector('label');
                return `${label.textContent} must match ${matchedLabel.textContent}`;
            }
        },
        {
            attribute: 'pattern',
            isValid: input => {
                const patternRegex = new RegExp(input.pattern);
                return patternRegex.test(input.value);
            },
            errorMessage: (input, label) => {
                if (label.textContent.trim() === 'Password') {
                    return 'Password must contain at least one uppercase letter, one lowercase letter, one number and one special character';
                }
                return `${label.textContent} is invalid`;
            }
        },
        {
            attribute: 'minlength',
            isValid: input => input.value && input.value.length >= parseInt(input.getAttribute('minlength')),
            errorMessage: (input, label) => `${label.textContent} must be at least ${input.getAttribute('minlength')} characters long`
        },
        {
            attribute: 'maxlength',
            isValid: input => input.value && input.value.length <= parseInt(input.getAttribute('maxlength')),
            errorMessage: (input, label) => `${label.textContent} must be maximum ${input.getAttribute('maxlength')} characters long`
        },
        {
            attribute: 'required',
            isValid: input => input.value.trim() !== '',
            errorMessage: (input, label) => `${label.textContent} is required`
        }
    ];

    formElement.setAttribute('novalidate', '');

    /**
     * Validate a single form group checking all validation options (if it has any)
     * Returns true if the form group passes all the validation options
     * @param formGroup
     * @returns {boolean}
     */
    const validateSingleFormGroup = formGroup => {
        const label = formGroup.querySelector('label');
        const input = formGroup.querySelector('input, textarea, select');
        const errorContainer = formGroup.querySelector('.error');
        const errorIcon = formGroup.querySelector('.error-icon');
        const successIcon = formGroup.querySelector('.success-icon');
        let formGroupError = false;
        for (const option of validationOptions) {
            if (input.hasAttribute(option.attribute) && !option.isValid(input)) {
                errorContainer.textContent = option.errorMessage(input, label);
                input.classList.add('border-red');
                input.classList.remove('border-green');
                errorIcon.classList.add('visible');
                successIcon.classList.remove('visible');
                formGroupError = true;
            }
        }
        if (!formGroupError) {
            errorContainer.textContent = '';
            input.classList.add('border-green');
            input.classList.remove('border-red');
            errorIcon.classList.remove('visible');
            successIcon.classList.add('visible');
        }

        return !formGroupError;
    };

    /** Takes all form groups and validate each of them
     * If all form groups are valid, return true
     * @param formToValidate
     * @returns {boolean}
     */
    const validateAllFormGroups = formToValidate => {
        const formGroups = Array.from(formToValidate.querySelectorAll('.form-group'));
        let validForm = true;
        formGroups.forEach(formGroup => {
            if (!validateSingleFormGroup(formGroup)) {
                validForm = false;
            }
        });
        return validForm;
    }

    // On Submit, validate all form groups
    formElement.addEventListener('submit', (event) => {
        event.preventDefault();
        if (validateAllFormGroups(formElement)) {
            formElement.submit();
        }
    });
};

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('form').forEach(form => {
        const formId = form.getAttribute('id');
        if(formId) {
            validateForm(`#${formId}`);
        }
    });
});
