import { useState } from 'react';
import './App.css';

function App() {
  const [targetAmount, setTargetAmount] = useState('');
  const [denominations, setDenominations] = useState('');
  const [result, setResult] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const denomList = denominations
      .split(',')
      .map((v) => parseFloat(v.trim()))
      .filter((v) => !isNaN(v));

    try {
      const response = await fetch('http://localhost:8080/coin', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          targetAmount: parseFloat(targetAmount),
          denominations: denomList,
        }),
      });

      if (!response.ok) {
        const text = await response.text();
        throw new Error(text || 'API error');
      }

      const data = await response.json();
      setResult(data.coins);
    } catch (err) {
      console.error('API Error:', err);
      alert(err.message);
    }
  };

  return (
    <div className="container">
      <h1>CoinAPI UI</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Target Amount:
          <input
            type="number"
            step="0.01"
            value={targetAmount}
            onChange={(e) => setTargetAmount(e.target.value)}
            required
          />
        </label>
        <label>
          Coin Denominations (comma-separated):
          <input
            type="text"
            placeholder="e.g., 0.01,0.5,1,5,10"
            value={denominations}
            onChange={(e) => setDenominations(e.target.value)}
            required
          />
        </label>
        <button type="submit">Calculate Coins</button>
      </form>

      <div className="result">
        <h2>Result:</h2>
        {result.length > 0 ? (
          <ul>
            {result.map((coin, idx) => (
              <li key={idx}>{coin.toFixed(2)}</li>
            ))}
          </ul>
        ) : (
          <p>No result yet.</p>
        )}
      </div>
    </div>
  );
}

export default App;