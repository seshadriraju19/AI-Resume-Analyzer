import { useState } from "react";

function Login({ onLoginSuccess, onShowRegister }) {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch(
                "http://localhost:8080/api/auth/login",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        email,
                        password,
                    }),
                }
            );

            const data = await response.json();

            if (response.ok) {

                localStorage.setItem("token", data.token);

                onLoginSuccess(data.token);

            } else {

                setMessage(data.message || "Login failed");

            }

        } catch (error) {

            console.error(error);
            setMessage("Unable to connect to server");

        }
    };

    return (
        <div className="auth-container">

            <form className="auth-form" onSubmit={handleLogin}>

                <h2>Login</h2>

                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />

                <button type="submit">
                    Login
                </button>

                {message && <p>{message}</p>}

                <p>
                    Don't have an account?{" "}
                    <button
                        type="button"
                        onClick={onShowRegister}
                    >
                        Register
                    </button>
                </p>

            </form>

        </div>
    );
}

export default Login;