package general.enums;

public enum InterceptorType {
	
	NONE {
		@Override
		public String getDenominacao() {
			return "NONE";
		}	
	},INTERCEPTOR_LOG {
		@Override
		public String getDenominacao() {
			return "INTERCEPTOR_LOG";
		}	
	},
	
	TESTE {
		@Override
		public String getDenominacao() {
			return "TESTE";
		}
	};
	
	public abstract String getDenominacao();


}
