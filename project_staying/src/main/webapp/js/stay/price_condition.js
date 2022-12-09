$(document).ready(function(){
	function controlFromInput(fromSlider, fromInput, toInput, controlSlider) {
		const [from, to] = getParsed(fromInput, toInput);
		const min = fromInput.min;
		fillSlider(fromInput, toInput, '#C6C6C6', '#48a9ed', controlSlider);
		if (from > to) {
			fromSlider.value = to;
			fromInput.value = to;
		} else if(from <= min){
			fromSlider.value = min;
			fromInput.value = min;
		}else {
			fromSlider.value = from;
			fromInput.value = from;
		}
	}
		
	function controlToInput(toSlider, fromInput, toInput, controlSlider) {
		const [from, to] = getParsed(fromInput, toInput);
		const max = fromInput.max;
		fillSlider(fromInput, toInput, '#C6C6C6', '#48a9ed', controlSlider);
		setToggleAccessible(toInput);
		if(to > max) {
			toSlider.value = max;
			toInput.value = max;
		} else if (from <= to) {
			toSlider.value = to;
			toInput.value = to;
		} else {
			toInput.value = from;
			toSlider.value = from;
		}
	}

	function controlFromSlider(fromSlider, toSlider, fromInput) {
		const [from, to] = getParsed(fromSlider, toSlider);
		fillSlider(fromSlider, toSlider, '#C6C6C6', '#48a9ed', toSlider);
		if (from > to) {
			fromSlider.value = to;
			fromInput.value = to;
		} else {
			fromInput.value = from;
		}
	}

	function controlToSlider(fromSlider, toSlider, toInput) {
		const [from, to] = getParsed(fromSlider, toSlider);
		fillSlider(fromSlider, toSlider, '#C6C6C6', '#48a9ed', toSlider);
		setToggleAccessible(toSlider);
		if (from <= to) {
			toSlider.value = to;
			toInput.value = to;
		} else {
			toInput.value = from;
			toSlider.value = from;
		}
	}

	function getParsed(currentFrom, currentTo) {
		const from = parseInt(currentFrom.value, 10);
		const to = parseInt(currentTo.value, 10);
		return [from, to];
	}

	function fillSlider(from, to, sliderColor, rangeColor, controlSlider) {
		const rangeDistance = to.max-to.min;
		const fromPosition = from.value - to.min;
		const toPosition = to.value - to.min;
		controlSlider.style.background = `linear-gradient(
		to right,
		${sliderColor} 0%,
		${sliderColor} ${(fromPosition)/(rangeDistance)*100}%,
		${rangeColor} ${((fromPosition)/(rangeDistance))*100}%,
		${rangeColor} ${(toPosition)/(rangeDistance)*100}%, 
		${sliderColor} ${(toPosition)/(rangeDistance)*100}%, 
		${sliderColor} 100%)`;
	}

	function setToggleAccessible(currentTarget, min_price) {
		const toSlider = document.querySelector('#toSlider');
		if (Number(currentTarget.value) <= min_price ) {
			toSlider.style.zIndex = 2;
		} else {
			toSlider.style.zIndex = 0;
		}
	}

	const fromSlider = document.querySelector('#fromSlider');
	const toSlider = document.querySelector('#toSlider');
	const fromInput = document.querySelector('#fromInput');
	const toInput = document.querySelector('#toInput');
	fillSlider(fromSlider, toSlider, '#C6C6C6', '#48a9ed', toSlider);
	setToggleAccessible(toSlider, fromSlider.min);

	fromSlider.oninput = () => controlFromSlider(fromSlider, toSlider, fromInput);
	toSlider.oninput = () => controlToSlider(fromSlider, toSlider, toInput);
	fromInput.onchange = () => controlFromInput(fromSlider, fromInput, toInput, toSlider);
	toInput.onchange = () => controlToInput(toSlider, fromInput, toInput, toSlider);
});