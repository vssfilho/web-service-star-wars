package com.b2wdigital.util;

/**
 * Classe usada no retorno do webservice rest, retornando apenas dois itens
 * status (sucesso e erro) e data (objects ou string de mensagens)
 * 
 * Utilização: Response.builder().
 * 				setError(). ou setSuccess().
 * 				setData()
 * 				.build()
 */
public final class Response {

	private final String status;
	private final Object data;
	
	private Response(Builder builder) {
		status = builder.status;
		data = builder.data;
	}
	
	public String getStatus() {
		return status;
	}

	public Object getData() {
		return data;
	}

	public static Builder builder() {
        return new Builder();
    }

	public static final class Builder {
		
		private String status;
		private Object data;
		
		public Builder setData(final Object data) {
			this.data = data;
			return this;
		}
		
		public Builder setError() {
			this.status = "error";
			return this;
		}
		
		public Builder setSuccess() {
			this.status = "success";
			return this;
		}
		
		public Response build() {
            return new Response(this);
        }
		
	}
	
}
