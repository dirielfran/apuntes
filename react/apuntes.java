UnitTest******************************************************************
	* Instalaciones*******************************************************
		Jest is a delightful JavaScript Testing Framework with a focus on simplicity.
		--> https://jestjs.io/
			--> npm install --save-dev jest
			--> npm add -D @types/jest

			* Add the following section to your package.json:

				{
				  "scripts": {
				    "test": "jest"  //or  "test": "jest --watchAll"
				  }
				}

			* run test 
				--> npm run test

			* whatwg-fetch

				En entornos de pruebas, específicamente si estás usando bibliotecas 
				de pruebas que emulan navegadores, es posible que fetch no esté soportado 
				y necesitas un polyfill para evitar errores durante las pruebas.
				
				* Se crea archivo jest.config.js 
					module.exports = {
					    testEnvironment: 'jest-environment-jsdom',
					    setupFiles: ['./jest.setup.js']
					}

				* Se agrega jest.setup.js
					import 'whatwg-fetch';

				--> npm add -D whatwg-fetch

		* Instalacion de Testing Library
			Se usa para las pruebas de los componentes
				--> npm add --dev @testing-library/react

		* Se intala babel
			@babel/core es el núcleo de Babel, el compilador de JavaScript 
			que te permite usar la última sintaxis de JavaScript no soportada 
			en todos los entornos. Babel traduce las características más modernas 
			de JavaScript (por ejemplo, ES6/ES7) a una versión que es compatible 
			con entornos más antiguos.

			--> npm install --save-dev babel-jest @babel/core @babel/preset-env

			Configure Babel to target your current version of Node by creating a babel.config.js file in the root of your project:

				--> babel.config.js
					module.exports = {
					  presets: [['@babel/preset-env', {targets: {node: 'current'}}]],
					};
	**********************************************************************
	* Sentencias de consola***********************************************
		* npm run test --> ejecucion de pruebas
		* npm run dev  --> ejecutar en ambiente dev
	**********************************************************************
	expect****************************************************************
		Condicion para que pase la pruebas
		Example: 

			describe('Pruebas en <DemoComponent />', () => {
			    test('Esta prueba no debe de fallar', () => {
			    
			        // 1. inicialización
			        const message1 = 'Hola Mundo';
			    
			        // 2. estímulo
			        const message2 = message1.trim();
			        
			        // 3. Observar el comportamiento... esperado
			        expect( message1 ).toBe( message2 );
			    
			    });			    
			});
	**********************************************************************

**************************************************************************




