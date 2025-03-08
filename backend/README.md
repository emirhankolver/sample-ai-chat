# SampleAI Backend

SampleAI is a simple AI-powered backend server built using **Nest.js**. It provides a single API
endpoint for querying an AI assistant with a prompt and message history.

## Features

- Built with **Nest.js**
- Single API endpoint for AI interactions
- Accepts conversation history to maintain context
- Returns responses as a **string stream**

## Installation

### Prerequisites

- [Node.js](https://nodejs.org/) (Recommended: latest LTS version)
- [npm](https://www.npmjs.com/) or [yarn](https://yarnpkg.com/)

### Steps

1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/sampleai-backend.git
   cd sampleai-backend
   ```
2. Install dependencies:
   ```sh
   npm install
   # or
   yarn install
   ```
3. Configure OpenAI API settings:
    - Open `src/clients/open-ai.client.ts`
    - Update the following section with your **baseURL** and **apiKey**:
      ```ts
      export class OpenAIClient {
        private readonly client: OpenAI;
      
        constructor() {
          this.client = new OpenAI({
            baseURL: process.env.OPEN_AI_BASE_URL, // Replace with your API endpoint
            apiKey: process.env.OPEN_AI_API_KEY, // Load API key from environment variables
          });
        }
      }
      ```
    - Set your API key as an environment variable:
      ```sh
      export OPEN_AI_BASE_URL=your_base_url_here
      export OPEN_AI_API_KEY=your_api_key_here
      ```

4. Start the server:
   ```sh
   npm run start
   # or
   yarn start
   ```
5. The server will start at `http://localhost:3000`

## API Usage

### Endpoint

```
POST http://localhost:3000/api/v1/ai/query
```

### Request Headers

```json
{
  "Content-Type": "application/json"
}
```

### Request Body

```json
{
  "prompt": "How would you explain Jetpack Compose to a 5-year-old?",
  "messageHistory": [
    {
      "isUserCreated": false,
      "message": "Hi! I'm SampleAl, your friendly and helpful assistant..."
    },
    {
      "isUserCreated": true,
      "message": "Hi! Can you introduce yourself?"
    }
  ]
}
```

### Response

The response is streamed as a **string**, rather than a JSON object.

#### Example Streamed Response

```
Jetpack Compose is like building with Lego blocks but for apps. Instead of writing long instructions, you just pick and place the blocks to create something fun!
```

## License

This project is licensed under the **MIT License**.

## Contact

For any questions or contributions, feel free to reach out
via [GitHub](https://github.com/your-repo).

